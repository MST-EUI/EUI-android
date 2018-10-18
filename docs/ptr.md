# PullToRefresh

> 下拉刷新/上拉加载更多 组件说明文档

### 效果图

 ##### 下拉刷新
 <img src="./ptr_1.gif" width="300" height="530">

 ##### 下拉加载更多
 <img src="./ptr_2.gif" width="300" height="530">

### 使用方式
<pre>
  配合SmartRefresh使用。
  SmartRefresh的使用文档请参考：https://github.com/scwang90/SmartRefreshLayout

  在SmartRefreshLayout中定制Header和Footer：

  < com.scwang.smartrefresh.layout.SmartRefreshLayout
          android:id="@+id/refresh_layout"
          android:layout_width="match_parent"
          android:layout_height="match_parent"
          app:srlEnableFooterFollowWhenLoadFinished="true">

          < com.mistong.android.commonui.ptr.EUIPtrHeader
              android:layout_width="match_parent"
              android:layout_height="wrap_content"/>

          < !-- content -->

          < com.mistong.android.commonui.ptr.EUIPtrFooter
              android:layout_width="match_parent"
              android:layout_height="wrap_content"/>

  < /com.scwang.smartrefresh.layout.SmartRefreshLayout>
</pre>