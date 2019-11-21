<p>
  <a href="https://developer.android.com/reference/android/os/Build.VERSION_CODES.html#ICE_CREAM_SANDWICH"><img src="https://img.shields.io/badge/API-14%2B-blue.svg?style=flat" alt="API" /></a>
  <a href="javascript:void(0);"><img src="https://img.shields.io/badge/Version-v1.6-brightgreen.svg" alt="Library version" /></a>
  <a href="http://www.methodscount.com/?lib=com.classic.common%3Amultiple-status-view%3A1.6"><img src="https://img.shields.io/badge/Methods count-52-e91e63.svg"/></a>
  <a href="http://www.methodscount.com/?lib=com.classic.common%3Amultiple-status-view%3A1.6"><img src="https://img.shields.io/badge/Size-7 KB-e91e63.svg"/></a>
  <a href="LICENSE.txt"><img src="https://img.shields.io/npm/l/express.svg?maxAge=2592000" alt="License" /></a>
</p>

一个支持多种状态的自定义View,可以方便的切换到：
- 加载中视图
- 错误视图
- 空数据视图
- 网络异常视图
- 内容视图

[apk下载](https://github.com/qyxxjd/MultipleStatusView/blob/master/apk/MultipleStatusView.apk?raw=true)

![](https://github.com/qyxxjd/MultipleStatusView/blob/master/screenshots/demo.gif)

## 使用

```gradle
dependencies {
    implementation 'com.classic.common:multiple-status-view:1.6'
}
```

## 示例

```xml
<com.classic.common.MultipleStatusView
    android:id="@+id/multiple_status_view"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:loadingView="@layout/custom_loading_view"
    app:emptyView="@layout/custom_empty_view"
    app:errorView="@layout/custom_error_view"
    app:noNetworkView="@layout/custom_no_network_view"
    app:contentView="@layout/main_content"/>
```

```java
MultipleStatusView multipleStatusView = (MultipleStatusView) findViewById(R.id.multiple_status_view);

//显示加载中视图
multipleStatusView.showLoading();
// multipleStatusView.showLoading(R.layout.xxx, layoutParams);
// multipleStatusView.showLoading(customView, layoutParams);

//显示空视图
multipleStatusView.showEmpty();
// multipleStatusView.showEmpty(R.layout.xxx, layoutParams);
// multipleStatusView.showEmpty(customView, layoutParams);

//显示错误视图
multipleStatusView.showError();
// multipleStatusView.showError(R.layout.xxx, layoutParams);
// multipleStatusView.showError(customView, layoutParams);

//显示无网络视图
multipleStatusView.showNoNetwork();
// multipleStatusView.showNoNetwork(R.layout.xxx, layoutParams);
// multipleStatusView.showNoNetwork(customView, layoutParams);

//显示内容视图
multipleStatusView.showContent();
// multipleStatusView.showContent(R.layout.xxx, layoutParams);
// multipleStatusView.showContent(customView, layoutParams);

//设置重试视图点击事件
multipleStatusView.setOnRetryClickListener(onRetryClickListener);

//设置视图状态切换监听事件
mMultipleStatusView.setOnViewStatusChangeListener(OnViewStatusChangeListener);

/**
* 获取当前view的状态
*      MultipleStatusView.STATUS_LOADING   //当前为加载中视图
*      MultipleStatusView.STATUS_EMPTY     //当前为空视图
*      MultipleStatusView.STATUS_ERROR     //当前为错误视图
*      MultipleStatusView.STATUS_NO_NETWORK//当前为无网络视图
*      MultipleStatusView.STATUS_CONTENT   //当前为内容视图
*/
int viewStatus = multipleStatusView.getViewStatus();

```

`MultipleStatusView` 继承自 `RelativeLayout`，所以内容视图也可以直接写在 `MultipleStatusView` 内部
```xml
<com.classic.common.MultipleStatusView
    android:id="@+id/multiple_status_view"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:emptyView="@layout/custom_empty_view"
    app:errorView="@layout/custom_error_view"
    app:loadingView="@layout/custom_loading_view"
    app:noNetworkView="@layout/custom_no_network_view">

    <ImageView
        android:id="@+id/image"
        android:layout_width="60dp"
        android:layout_height="60dp"
        android:layout_centerVertical="true"
        android:layout_marginLeft="24dp"
        android:layout_marginRight="16dp"
        android:src="@drawable/test"/>

    <TextView
        android:id="@+id/text1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignTop="@id/image"
        android:layout_toRightOf="@id/image"
        android:text="内容视图111111"/>

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignBottom="@id/image"
        android:layout_toRightOf="@id/image"
        android:text="内容视图222222"/>

</com.classic.common.MultipleStatusView>
```

## 注意事项

#### 1. 如果使用自定义属性
```
app:emptyView="@layout/..."
app:errorView="@layout/..."
app:loadingView="@layout/..."
app:noNetworkView="@layout/..."
```
需要设置：

- 加载中视图的id必须为：`loading_view`
- 空视图的id必须为：`empty_view`
- 错误视图的id必须为：`error_view`
- 无网络视图的id必须为：`no_network_view`


#### 2. 如果需要点击某个 `view` 进行重试, 需要设置:

- 空视图内对应的view id：`empty_retry_view`
- 错误视图内对应的view id：`error_retry_view`
- 无网络视图内对应的view id：`no_network_retry_view`


#### 3. 使用 `new` 关键字创建自定义视图时，请设置 `id`

```xml
TextView tv = new TextView(getApplicationContext());
tv.setId(Utils.generateViewId());
tv.setText(text);
```

更多使用方法详见 `demo` 示例:

- [简单布局](https://github.com/qyxxjd/MultipleStatusView/blob/master/app/src/main/res/layout/activity_simple.xml)
- [简单布局2](https://github.com/qyxxjd/MultipleStatusView/blob/master/app/src/main/res/layout/activity_simple2.xml)
- [设置自定义视图](https://github.com/qyxxjd/MultipleStatusView/blob/master/app/src/main/java/com/classic/common/simple/CustomActivity.java)
- [列表页面](https://github.com/qyxxjd/MultipleStatusView/blob/master/app/src/main/res/layout/activity_list.xml)
- [列表 + 下拉刷新](https://github.com/qyxxjd/MultipleStatusView/blob/master/app/src/main/res/layout/activity_refresh.xml)


## 贡献者

感谢以下人员贡献的代码
- [Lindroy](https://github.com/Lindroy)


## 关于

* Blog: [http://blog.csdn.net/qy1387](http://blog.csdn.net/qy1387)
* Email: [pgliubin@gmail.com](http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=pgliubin@gmail.com)

## License

```
Copyright 2015 classic

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.  IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
```
