# AnimationDemo
>AnimationDemo项目是一个ListView多选动画的列子，动画是用利用Camera实现中轴3D翻转（具体实现可以参考Rotate3dAnimation类）

## 代码解析

	在枚举AnimationTool中集成了
1.void setDuration(int duration)

2.void setDepth(int depth)

3.void startSelectAnimation(ImageView view, int resId)

4.void startCancleAnimation(ImageView view, int resId)

	4 个公共方法便于设置动画播放。
