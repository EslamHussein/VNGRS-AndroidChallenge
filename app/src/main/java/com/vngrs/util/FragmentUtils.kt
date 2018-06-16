package com.vngrs.util

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

/**
 * Created by Eslam Hussein on 6/15/18.
 */


enum class FragmentTransaction {
    ADD, Replace
}

class FragmentUtils {


    companion object {
        fun transact(activity: AppCompatActivity, mFragment: Fragment, @IdRes container: Int,
                     isNeedToAddToStack: Boolean = false, transaction: FragmentTransaction = FragmentTransaction.Replace,
                     fragmentTag: String? = null) {
            var fragment = mFragment

            val fragTag = fragmentTag ?: fragment::class.simpleName
            val manager = activity.supportFragmentManager
            val isInStack = manager.popBackStackImmediate(fragTag, 0)
            val ft = manager.beginTransaction()

            if (isInStack) {
                fragment = manager.findFragmentByTag(fragTag)!!
            }
            when (transaction) {

                FragmentTransaction.ADD -> ft.add(container, fragment, fragTag)
                FragmentTransaction.Replace -> ft.replace(container, fragment, fragTag)

            }

            if (!isInStack && isNeedToAddToStack) {
                ft.addToBackStack(fragTag)
            }
            ft.commit()
        }


    }

}