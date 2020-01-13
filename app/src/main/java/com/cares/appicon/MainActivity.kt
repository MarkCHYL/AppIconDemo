package com.cares.appicon

import android.content.ComponentName
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun changeOne(view: View) {
        Toast.makeText(this, "切换图标mark", Toast.LENGTH_SHORT).show()
        changeAppName(1)

    }

    fun changeTwo(view: View) {
        Toast.makeText(this, "恢复mark", Toast.LENGTH_SHORT).show()
        changeAppName(2)
    }


    fun changeThree(view: View) {
        Toast.makeText(this, "切换图标chyl", Toast.LENGTH_SHORT).show()
        changeAppName(3)
    }


    fun changeback(view: View) {

        Toast.makeText(this, "恢复图标chyl", Toast.LENGTH_SHORT).show()
        changeAppName(4)
    }


    fun changeNone(view: View) {

        Toast.makeText(this, "啥事都不做", Toast.LENGTH_SHORT).show()
    }

    /**
     * =1、第一图标状态 =2 第二图标状态 =3、不换图标
     */
    fun changeAppName(tag: Int) {
        val mark_tag = "com.cares.appicon.mark"
        val chyl_tag = "com.cares.appicon.chyl"
        //取消掉默认的应用icon
        val pm: PackageManager = packageManager

        //然后执行
        when (tag) {
            1 -> {
                pm.setComponentEnabledSetting(
                    componentName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                    PackageManager.DONT_KILL_APP
                )

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


}
