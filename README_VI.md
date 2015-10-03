Ngôn ngữ khác: [English](README.md) | [Chinese] (README_CN.md)
###TÀI LIỆU HƯỚNG DẪN TÍCH HỢP HỆ THỐNG THANH TOÁN INGAMESDK

**Giới thiệu**

InGameSDKPayment là hệ thống tích hợp tài khoản và thanh toán cho ứng dụng của bạn. SDK này cung cấp giải pháp cho các hình thức thanh toán: SMS, Thẻ cào điện thoại, Internet banking và Google Payment.

**Các bước tích hợp SDK:**

​1. Tải hệ thống SDK

​2. Các bước tích hợp và cấu hình

​3. Cách thức khai báo, khởi tạo và gọi các chức năng của SDK từ ứng dụng của bạn

​4. Chạy ứng dụng minh họa

<hr/>

### I. Tải hệ thống SDK

Tải InGame SDK tại địa chỉ:  https://github.com/InGameVietnam/Android-InGameSDKPayment.git

Hoặc sử dụng công cụ git tool để clone từ đường dẫn này: https://github.com/InGameVietnam/Android-InGameSDKPayment.git

###II. Các bước tích hợp và cấu hình
####A. Tích hợp: 
Bộ SDK khi tải về bao gồm 2 phần:

![add](http://i757.photobucket.com/albums/xx212/ichirokudo/Ingame/Picture1_zpsczrmwmy4.png)
   
  <i>Cấu trúc thư mục</i>

- <b>Các thư viện </b>(requirement libraries): là các tập tin có đuôi <b>*.jar</b> bên trong thư mục <b>libs/</b> 
  
Sao chép các tập tin <b>*.jar</b> này vào thư mục <b>libs/</b>  trong project <i>(nếu chưa có thư mục <b>libs/</b>  bạn có thể tạo mới sau đó chép những file này vào)</i>
	
- <b>Resource </b>(res folder): là những tài nguyên cần thiết của thư viện <b>ingamesdk.jar </b>
    
Tương tự như trên bạn chỉ cần sao chép và dán vào thư mục <b>res/</b> trong ứng dụng của bạn.

####B. Cấu hình:

<b>[Quan trọng] Bạn cần có thông tin sau đây trước khi tiến hành cấu hình:</b> <br/>
 <b><i>*Lấy giá trị``` <application_license_key>``` từ Google</b></i><br/>
>  1> Đăng nhập vào <b>Google Play Developer Console</b> của bạn.<br/>
>  2>  Nhấn chọn <b>All Applications</b> và tìm ứng dụng bạn cần lấy thông tin.<br/>
> 3>Sau đó, đến mục <b>Services and APIs</b><br/>
>![add](http://i757.photobucket.com/albums/xx212/ichirokudo/Ingame/Picture2_zpsoquddje9.jpg)<br/>
 Bạn sẽ tìm thấy giấy phép của ứng dụng (license key) dưới mục <b>Your License Key For this Application</b>.

<b>[Lưu ý] Bên dưới sẽ có những vị trí nằm trong < > bạn cần thay thế chúng như sau:</b>
> Thay ```<your_package_name>``` bằng ```packagename của ứng dụng```.<br/>
> Thay ```<your_facebook_application_id>``` bằng ```facebook app id``` của bạn.<br/>
> Thay ```<application_license_key>```bằng ```giấy phép ứng dụng (license key)``` của bạn.<br/>
> Thay ```<ingame_application_id>```bằng ```app id được cấp bởi ingame``` của bạn.<br/>
> Thay ```<ingame_application_key>```bằng ```app key được cấp bởi ingame``` của bạn.<br/>



<b>Cấu hình strings.xml</b>

　　Mở tập tin <b>strings.xml</b> trong project Android ứng dụng
　　
``` 
　　　
　　　　<string name="google_license_key"><application_license_key></string>
　　　　<string name="App_Id"><ingame_application_id></string>
　　　　<string name="App_Key"><ingame_application_key></string>
　　　
``` 
<b>Cấu hình AndroidMainfest.xml</b>

　　Mở tập tin <b>AndroidMainfest.xml</b> trong project Android ứng dụng
-   Thêm các thẻ ```<permission>``` để cấp quyền truy cập cho hệ thống:

```
    <uses-permission android:name="android.permission.SEND_SMS" />
　　<uses-permission android:name="android.permission.INTERNET" />
　　<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
　　<uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />
　　<uses-permission android:name="android.permission.READ_PHONE_STATE" />
　　<uses-permission android:name="android.permission.WAKE_LOCK" />
　　<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
　　<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />

　　<!-- Google IAP Permission -->
    	<uses-permission android:name="android.permission.GET_ACCOUNTS" />
　　<uses-permission android:name="com.android.vending.BILLING" />
```
- Thêm các thẻ ```<meta-data>``` bên trong thẻ ```<application>``` để cấu hình các giá trị cho hệ thống:

```
　　<application
　　　　...........................
　　　
　　　　<meta-data
　　　　　　android:name="com.google.android.gms.version"
　　　　　　android:value="@integer/google_play_services_version" />
　　　　<meta-data
　　　　　　android:name="com.IngameSDK.GoogleIAPKey"
　　　　　　android:value="@string/google_license_key" />
　　　　<meta-data android:name="com.IngameSDK.AppId" 
　　　　    android:value="@string/App_Id" />
    <meta-data android:name="com.IngameSDK.AppKey" 
        android:value="@string/App_Key" />


     <!--  for app flyer -->
        <receiver android:exported="true" android:name="com.appsflyer.MultipleInstallBroadcastReceiver">
            <intent-filter>
                <action android:name="com.android.vending.INSTALL_REFERRER"/>
            </intent-filter>
        </receiver>
        <receiver android:name="com.appsflyer.AppsFlyerLib">
            <intent-filter>
                <action android:name="android.intent.action.PACKAGE_REMOVED"/>
                <data android:scheme="package"/>
            </intent-filter>
        </receiver>
        <meta-data android:name="com.appflyer.dev_key" 
             android:value="ekymUhihizGufaXWaeH5nn" />
        <!--  end for app flyer -->
　　　　...........................
　　</application>
```
- Thêm các thẻ ```<activity>``` để cấu hình các giao diện hệ thống:

```
　　<application>
　　　　...........................
  
        <activity
            android:name="com.ingamesdk.ui.PaymentActivity"
            android:configChanges="orientation|keyboardHidden|screenSize"
            android:theme="@style/UserDialog"
            android:windowSoftInputMode="adjustPan" />
       
　　　　...........................
　　</application>
```
###III. Cách thức khai báo, khởi tạo và gọi các chức năng của SDK từ ứng dụng của bạn

<b>Khai báo các biến sau vào bên trong lớp Activity chính của ứng dụng:</b>

	public static InGameSDK ingame_sdk = InGameSDK.getInstance(); // instance của InGameSDK

<b>Thiết lập các giá trị cho InGameSDK bên trong hàm onCreate(...)</b>

	ingame_sdk.callSendInstallationEvent(this); //Hàm này phải được gọi trong hàm OnCreate() và trước hàm Init của SDK

	ingame_sdk.init(this, callback_url);


<b>Các tham số của hàm ingame_sdk.init(...)</b>


　　public void init(Activity context, String callback_url) {...}


>```context```:  Activity chính của ứng dụng<br/>
>```callback_url```: Đường link gọi callback của Server<br/>

<b>Thêm các xử lý sau vào tương ứng từng hàm</b>
    
    @Override
        protected void onResume() {
            super.onResume();
            InGameSDK.getInstance().onResume(this);
        }

<b>Gọi hàm ```callPayment``` để thực hiện chức năng thanh toán:</b>


```

ingame_sdk.callPayment(String game_order); 
// game_order:Mã giao dịch do Nhà phát triển tự tạo ra (nhỏ hơn 50 ký tự)..<br/>

```


####IV. Ứng dụng minh họa

Ứng dụng minh họa đi kèm SDK là một ví dụ đơn giản cho việc tích hợp. 

