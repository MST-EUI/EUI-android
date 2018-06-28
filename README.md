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
    builder.create().show();</pre>
    其中 builder.create()返回对应dialog，若要执行关闭操作返回dialog使用dialog.dismiss。
             
  
  
