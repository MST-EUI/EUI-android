# list 组件

> list 组件说明文档

### 效果图

### 使用方式
<pre>
  < com.mistong.android.commonui.list.InfoView
              android:id="@+id/inf_1"
              android:layout_width="match_parent"
              android:layout_height="wrap_content"
              app:showLeftImage="true"
              app:leftIcon="@drawable/eui_button_bg"
              app:mainText="标题文字标题文字标题文字标题文字标题文字标题文字"
              app:rightMode="arrow"
              />
</pre>

###### 属性
 | 属性 | 说明 | 类型 | 默认 |
 | ------------- |------------- | ----- |
 | showBottomDivider | 是否显示底部分割线 | boolean | true |
 | showLeftImage | 是否显示右侧icon | boolean | false |
 | showSubText | 是否显示副标题 | boolean | false |
 | LeftImageSize | 右侧icon的大小 | enum | 'normal' |
 | mainText | 主标题文本 | string | "" |
 | subText | 副标题文本 | string | "" |
 | rightMode | 副标题文本 | enum | 'normal' |
 | rightIcon | 右侧icon资源id | reference | null |
 | leftIcon | 左侧icon资源id | reference | null |
 | rightMargin | 右侧布局右边距 | dimension | 12dp |
 | rightText | 右侧文本 | string | "" |

###### Api
- setRightToggleListener() 为右侧toggleButton设置状态改变监听
