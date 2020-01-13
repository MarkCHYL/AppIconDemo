# AppIconDemo
动态切换app图标和名字
实现之前觉得万分难，我查阅了很多博客.
报错-----应用未安装
Stop，停下来我先抽支烟，喝杯茶，仔细看看别人的介绍。再次感谢这个博主的博客[倚栏静望](https://blog.csdn.net/ozhuimeng123/article/details/92734825)

[本文链接](http://markchyl.cn/2020/01/13/Android%E5%8A%A8%E6%80%81%E6%9B%B4%E6%8D%A2APP%E5%9B%BE%E6%A0%87%E5%8F%8A%E5%90%8D%E7%A7%B0/)
**实现的目的和标题一样，动态的实现app的启动图标和应用的名字**
### 下面我先贴出我的重要代码
* 在*AndroidManifest.xml*文件中添加:
  ```
   <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme"
        tools:ignore="GoogleAppIndexingWarning">
        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>

        <activity-alias
            android:name=".mark"
            android:enabled="false"
            android:icon="@mipmap/mark_launcher"
            android:label="@string/app_name1"
            android:roundIcon="@mipmap/mark_launcher_round"
            android:targetActivity=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity-alias>

        <activity-alias
            android:name=".chyl"
            android:enabled="false"
            android:icon="@mipmap/chyl_launcher"
            android:label="@string/app_name2"
            android:roundIcon="@mipmap/chyl_launcher_round"
            android:targetActivity=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity-alias>
    </application>
    ```
* 在 MainActivity中编写切换代码：
  ```
  /**
     * =1、第一图标状态 =2 第二图标状态 =3、不换图标
     */
    fun changeAppName(tag: Int) {
        val mark_tag = "com.cares.appicon.mark"
        val chyl_tag = "com.cares.appicon.chyl"
        
        val pm: PackageManager = packageManager

        when (tag) {
            1 -> {
                //取消掉默认的应用icon
                pm.setComponentEnabledSetting(
                    componentName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                    PackageManager.DONT_KILL_APP
                )
                //然后执行
                pm.setComponentEnabledSetting(
                    ComponentName(
                        baseContext,
                        mark_tag
                    ),
                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                    PackageManager.DONT_KILL_APP
                )

            }
            2 -> {
                pm.setComponentEnabledSetting(
                    ComponentName(baseContext, mark_tag),
                    PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                    PackageManager.DONT_KILL_APP
                )
                pm.setComponentEnabledSetting(
                    ComponentName(
                        baseContext,
                        packageName + ".MainActivity"
                    ),
                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                    PackageManager.DONT_KILL_APP
                )
            }
            3 -> {
                pm.setComponentEnabledSetting(
                    componentName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                    PackageManager.DONT_KILL_APP
                )

                pm.setComponentEnabledSetting(
                    ComponentName(
                        baseContext,
                        chyl_tag
                    ),
                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                    PackageManager.DONT_KILL_APP
                )

            }
            4 -> {
                pm.setComponentEnabledSetting(
                    ComponentName(baseContext, chyl_tag),
                    PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                    PackageManager.DONT_KILL_APP
                )
                pm.setComponentEnabledSetting(
                    ComponentName(
                        baseContext,
                        packageName + ".MainActivity"
                    ),
                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                    PackageManager.DONT_KILL_APP
                )
            }
        }

    }
    ```
### 解释下里面的重要词：
activity-alias功能

activity-alias作为一个已存在Activity的别名，则应该可以通过该别名标签声明快速打开目标Activity。因此activity-alias可用来设置某个Activity的快捷入口，可以放在桌面上或者通过该别名被其他组件快速调起。该标签元素支持一些属性及intent-filter、meta-data等配置，因此可以触发一些跟目标Activity不同的功能逻辑，虽然打开的是同一个Activity。举个简单的例子，如之前需要先打开主界面，然后才能点击进入某个Activity，如果使用activity-alias为该Activity配置一个快捷入口，甚至可以为其在桌面生成一个图标，然后点击桌面图标可直接进入该Activity，该功能可满足某些需要快速到达功能界面的需求。

语法：
```
<activity-alias android:enabled=["true" | "false"]
                android:exported=["true" | "false"]
                android:icon="drawable resource"
                android:label="string resource"
                android:name="string"
                android:permission="string"
                android:targetActivity="string" >
    . . .
</activity-alias>
```
* android:enable
该属性用来决定目标Activity可否通过别名被系统实例化，默认为true。需要注意的是application也有enable属性，只用当它们同时为true时，activity-alias的enable才生效。
* android:exported
该属性为true的话，则目标Activity可被其他应用调起，如为false则只能被应用自身调起。其默认值根据activity-alias是否包含intent-filter元素决定，如果有的话，则默认为true；没有的话则为false。其实也很好理解，如果有intent-filter，则目标Activity可以匹配隐式Intent，因此可被外部应用唤起；如果没有intent-filter，则目标Activity要被调起的话必须知道其精确类名，因为只有应用本身才知道精确类名，所以此时默认为false。
* android:icon
该属性就比较好玩了，允许自定义icon，可以不同于应用本身在桌面的icon。如果需要在桌面上创建快捷入口，也许产品会要求换个不同的icon。
* android:label
该属性类似于android:icon，图标都换了，换个名称也合情合理吧，此属性就是为此而生的。
* android:name
该属性可以为任意字符串，但最好符合类名命名规范。activity元素的name属性实质上都会指向一个具体的Activity类，而activity-alias的name属性仅作为一个唯一标识而已。
* android:permission
该属性指明了通过别名声明调起目标Activity所必需的权限。
* android:targetActivity
该属性指定了目标Activity，即通过activity-alias调起的Activity是哪个，此属性其实类似于activity标签中的name属性，需要规范的Activity包名类名。
