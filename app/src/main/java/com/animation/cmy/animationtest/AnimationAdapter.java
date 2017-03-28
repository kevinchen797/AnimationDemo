package com.animation.cmy.animationtest;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by chen.mingyao on 2016/12/20.
 */

public class AnimationAdapter extends BaseAdapter {

    private List<People> peopleList = new ArrayList<>();
    private Context mContext;
    private Set<String> selectOptions = new HashSet<>();
    private Set<Integer> positionSet = new HashSet<>();

    public AnimationAdapter(Context context, List<People> peopleList) {
        this.peopleList = peopleList;
        mContext = context;
    }

    @Override
    public int getCount() {
        return peopleList.size();
    }

    @Override
    public Object getItem(int position) {
        return peopleList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("cmy", "getView()");
        final People people = peopleList.get(position);
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_select_animation, null);
            holder.ivPhoto = (ImageView) convertView.findViewById(R.id.iv_photo);
            holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            holder.tvAge = (TextView) convertView.findViewById(R.id.tv_age);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();


        if (selectOptions.contains(people.getName())) {//选中
            if (positionSet.contains(position)) {//播放过开始动画
                holder.ivPhoto.setImageResource(R.drawable.groups_user_select);
            } else {//未播放过开始动画
                //TODO 播放开始动画
                AnimationTool.INSTANCE.startSelectAnimation(holder.ivPhoto, R.drawable.groups_user_select);
                positionSet.add(position);
            }
        } else {//未选中
            if (positionSet.contains(position)) {//未播放过结束动画
//                // TODO 播放结束动画
                AnimationTool.INSTANCE.startCancleAnimation(holder.ivPhoto, R.drawable.groups_user);
                positionSet.remove(position);
            } else {//播放过结束动画
                holder.ivPhoto.setImageResource(R.drawable.groups_user);
            }
        }

        holder.tvAge.setText(people.getAge()+"");
        holder.tvName.setText(people.getName());

        holder.ivPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectOptions.contains(people.getName())) {
                    selectOptions.remove(people.getName());
                    Log.d("cmy", "ClickListener CANCLE + 1");
                } else {
                    selectOptions.add(people.getName());
                    Log.d("cmy", "ClickListener SELECT + 1");
                }
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    class ViewHolder {
        TextView tvName, tvAge;
        ImageView ivPhoto;
    }

    public Set<String> getSelectOptions() {
        return selectOptions;
    }

    public void setSelectOptions(Set<String> selectOptions) {
        this.selectOptions = selectOptions;
    }

    public Set<Integer> getPositionSet() {
        return positionSet;
    }

    public void setPositionSet(Set<Integer> positionSet) {
        this.positionSet = positionSet;
    }

    /**
     * *****************************ListViewCheckListener*****************************
     * 头像点击事件传给实现者处理
     */
    private ListViewCheckListener listener;

    public void setInterface(ListViewCheckListener listener) {
        this.listener = listener;
    }

    public interface ListViewCheckListener {
        void ListViewCheckListener(Set<String> selectOptions);
    }


//    private void selectAnimation() {
//        // 获取布局的中心点位置，作为旋转的中心点
//        float centerX = imageView.getWidth() / 2f;
//        float centerY = imageView.getHeight() / 2f;
//        // 构建3D旋转动画对象，旋转角度为0到90度，这使得ListView将会从可见变为不可见
//        final Rotate3dAnimation rotation = new Rotate3dAnimation(0, 90, centerX, centerY,
//                310.0f, true);
//        // 动画持续时间500毫秒
//        rotation.setDuration(500);
//        // 动画完成后保持完成的状态
//        rotation.setFillAfter(true);
//        rotation.setInterpolator(new AccelerateInterpolator());
//        // 设置动画的监听器
//        rotation.setAnimationListener(new TurnToSelect());
//        imageView.startAnimation(rotation);
//    }
//
//    private void CancleAnimation() {
//        // 获取布局的中心点位置，作为旋转的中心点
//        float centerX = imageView.getWidth() / 2f;
//        float centerY = imageView.getHeight() / 2f;
//        // 构建3D旋转动画对象，旋转角度为360到270度，这使得ImageView将会从可见变为不可见，并且旋转的方向是相反的
//        final Rotate3dAnimation rotation = new Rotate3dAnimation(360, 270, centerX,
//                centerY, 310.0f, true);
//        // 动画持续时间500毫秒
//        rotation.setDuration(500);
//        // 动画完成后保持完成的状态
//        rotation.setFillAfter(true);
//        rotation.setInterpolator(new AccelerateInterpolator());
//        // 设置动画的监听器
//        rotation.setAnimationListener(new TurnToCancle());
//        imageView.startAnimation(rotation);
//    }
//
//    /**
//     * 注册在选中点击动画中的动画监听器。
//     *
//     * @author chenmingyao
//     */
//    class TurnToSelect implements Animation.AnimationListener {
//
//        @Override
//        public void onAnimationStart(Animation animation) {
//        }
//
//        @Override
//        public void onAnimationEnd(Animation animation) {
//            // 获取布局的中心点位置，作为旋转的中心点
//            float centerX = imageView.getWidth() / 2f;
//            float centerY = imageView.getHeight() / 2f;
//            imageView.setImageResource(R.drawable.groups_user_select);
//            // 构建3D旋转动画对象，旋转角度为270到360度，这使得ImageView将会从不可见变为可见
//            final Rotate3dAnimation rotation = new Rotate3dAnimation(270, 360, centerX, centerY,
//                    310.0f, false);
//            // 动画持续时间500毫秒
//            rotation.setDuration(500);
//            // 动画完成后保持完成的状态
//            rotation.setFillAfter(true);
//            rotation.setInterpolator(new AccelerateInterpolator());
//            imageView.startAnimation(rotation);
//
//            positionSet.add()
//        }
//
//        @Override
//        public void onAnimationRepeat(Animation animation) {
//        }
//
//    }
//
//    /**
//     * 注册在取消选择点击动画中的动画监听器。
//     *
//     * @author chenmingyao
//     */
//    class TurnToCancle implements Animation.AnimationListener {
//
//        @Override
//        public void onAnimationStart(Animation animation) {
//        }
//
//        @Override
//        public void onAnimationEnd(Animation animation) {
//            // 获取布局的中心点位置，作为旋转的中心点
//            float centerX = imageView.getWidth() / 2f;
//            float centerY = imageView.getHeight() / 2f;
//            imageView.setImageResource(R.drawable.groups_user);
//            // 构建3D旋转动画对象，旋转角度为90到0度，这使得ListView将会从不可见变为可见，从而回到原点
//            final Rotate3dAnimation rotation = new Rotate3dAnimation(90, 0, centerX, centerY,
//                    310.0f, false);
//            // 动画持续时间500毫秒
//            rotation.setDuration(500);
//            // 动画完成后保持完成的状态
//            rotation.setFillAfter(true);
//            rotation.setInterpolator(new AccelerateInterpolator());
//            imageView.startAnimation(rotation);
//        }
//
//        @Override
//        public void onAnimationRepeat(Animation animation) {
//        }
//
//    }
}
