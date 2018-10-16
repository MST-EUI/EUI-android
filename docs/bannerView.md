# BannerView 弹窗

> bannerView 组件说明文档

### 效果图
 <img src="./Screenshot_20180716-113314.gif" width="300" height="530">
### 使用方式

<pre>
 BannerEntity bannerEntity1 = new BannerEntity();
 bannerEntity1.path = "http://static.235.mistong.com/upload/ewt/image/2018/05/28/20180528153257941.png";
 BannerEntity bannerEntity2 = new BannerEntity();
 bannerEntity2.path = "http://static.235.mistong.com/upload/ewt/image/2018/07/16/20180716170654180.png";
 BannerEntity bannerEntity3 = new BannerEntity();
 bannerEntity3.path = "http://static.235.mistong.com/upload/ewt/image/2018/05/31/20180531180350744.jpg";
 datas.add(bannerEntity1);
 datas.add(bannerEntity2);
 datas.add(bannerEntity3);
 bannerView.setData(datas);
</pre>

### API 

 ##### layout中设置
 根布局定义 xmlns:app="http://schemas.android.com/apk/res-auto"
 
 | 属性 | 说明 | 类型 | 默认 |
 | ------------- |------------- | ----- |
 | isFull | 宽度是否全屏 | boolean | false |
 | imgRadius | 图片圆角大小 | dimension | 4dp |
 | isCycle | 是否循环 | boolean | true |
 | isWheel | 是否自动轮播 | boolean | true |
 | delay | 轮播时间 | integer | 3000ms |
 
 
 ##### 代码中设置
 - setData(List<BannerEntity> list) 设置数据
 - setData(List<BannerEntity> list,int showPosition) 设置数据和默认显示第几个
 - setCycle(boolean isCycle) 是否循环，需在setData前调用
 - setWheel(boolean isCycle) 是否轮播，轮播一定是循环的
 - refreshData() 刷新数据
 - setDelay(int delay) 轮播暂停时间，单位毫秒
 - setImageCycleViewListener(ImageCycleViewListener imageCycleViewListener) 轮播图片的点击事件
 