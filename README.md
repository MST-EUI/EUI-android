# EUI-android
  android控件标准化

# Dialog
  EUIHorizontalDialog（操作按钮并排）和EUIVerticalDialog（操作按钮竖排），使用方法类似。
 ## 使用方式
   <pre>EUIVerticalDialog.Builder builder = new EUIVerticalDialog.Builder(this);
                builder.setTitle("标题");
                builder.setContent("内容内容内容内容内容内容内容内容内容内容内容内容");
                builder.setPositiveButton("知道了", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "点我试试", Toast.LENGTH_SHORT).show();
                    }
                });
    builder.create().show();
    其中 builder.create()返回对应dialog，若要执行关闭操作返回dialog使用dialog.dismiss。</pre>
 ## 效果图
  <img src="https://github.com/MST-EUI/EUI-android/blob/master/screenshot/Screenshot_20180716-113325.jpg" width="300" height="530">
  <img src="https://github.com/MST-EUI/EUI-android/blob/master/screenshot/Screenshot_20180716-113332.jpg" width="300" height="530">
  <img src="https://github.com/MST-EUI/EUI-android/blob/master/screenshot/Screenshot_20180716-113338.jpg" width="300" height="530">
  <img src="https://github.com/MST-EUI/EUI-android/blob/master/screenshot/Screenshot_20180716-113344.jpg" width="300" height="530">
  <img src="https://github.com/MST-EUI/EUI-android/blob/master/screenshot/Screenshot_20180716-113350.jpg" width="300" height="530">
  <img src="https://github.com/MST-EUI/EUI-android/blob/master/screenshot/Screenshot_20180716-113355.jpg" width="300" height="530">
  
# Toast
  EUIToast
  ## 使用方式
    EUIToast.showLong(this, "单行吐司"); //长时间
    EUIToast.showShort(this, "单行吐司"); //短时间

# Button
  ## 使用方式
    style="@style/EUI_Button" //普通Button使用style EUI_Button
    style="@style/EUI_RadioButton" //RadioButton使用style EUI_RadioButton

# Loading
  EUILoadingDialog
  ## 使用方式
    EUILoadingDialog dialog = new EUILoadingDialog(this);
    dialog.show();
    
# empty
  EmptyLayout
  ## 使用方式
    emptyLayout.bindView(内容view); //绑定内容view
    emptyLayout.showSuccess(); //显示内容
    emptyLayout.showEmpty(); //内容为空时显示
    
    emptyLayout.showError();
    emptyLayout.setReloadListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ListActivity.this,"重新加载",Toast.LENGTH_LONG).show();
                 }
            }); //数据返回犯错显示

  
  
