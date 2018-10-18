# BottomTabs

> 底部栏 组件说明文档

### 效果图
 <img src="./bottomtabs/bottomtabs.gif" width="300" height="530">

### 使用方式
<pre>
  在布局文件中添加该组件后，添加拼装后的数据：
  List<BottomTabBean> bottomTabBeanList = getBeans();
  bottom_tab_layout.addTabs(bottomTabBeanList);
  BottomTabBean各个属性含义如下：
  id  ------------------ tab id
  title ---------------- 显示文字
  resId ---------------- 未选中时icon资源id (设置drawable后会被覆盖)
  activeResId ---------- 选中时icon资源id  (设置drawable后会被覆盖)
  textColor ------------ 未选中时文字颜色
  activeTextColor ------ 选中时文字颜色
  iconDrawable --------- 未选中时icon图片
  activeIconDrawable --- 选中时icon图片
</pre>

### Api

- addTabs() 添加tab，在初始化时使用，添加需要展示的tab
- setSelectIndex() 选中某个tab
- getSelectedIndex() 获得当前选中tab的位置
- setNotice() 为某个位置的tab设置红点提示
- setItemLabel() 改变某个位置的tab的文字
- setListener() 设置点击tab的事件监听器