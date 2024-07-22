package com.instana.android.shared

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * Base class for activities that use ViewBinding.
 * Provides convenient functions for managing fragments and view binding.
 */
abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    var _binding: VB? = null
    protected val binding: VB
        get() = _binding!!

    /**
     * Creates the view binding by inflating the layout using the provided lambda function.
     *
     * @param inflate The lambda function for inflating the layout.
     */
    protected inline fun <reified T : ViewBinding> createBinding(inflate: (LayoutInflater) -> VB) {
        _binding = inflate(layoutInflater)
        setContentView(binding.root)
    }

    /**
     * Replaces the fragment in the specified container view with the given fragment.
     *
     * @param containerViewId The ID of the container view.
     * @param fragment The fragment to replace.
     * @param addToBackStack Whether to add the transaction to the back stack.
     */
    protected fun replaceFragment(containerViewId: Int, fragment: Fragment, addToBackStack: Boolean = true) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(containerViewId, fragment)
        if (addToBackStack) {
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }

    /**
     * Adds the fragment to the specified container view.
     *
     * @param containerViewId The ID of the container view.
     * @param fragment The fragment to add.
     * @param addToBackStack Whether to add the transaction to the back stack.
     */
    protected fun addFragment(containerViewId: Int, fragment: Fragment, addToBackStack: Boolean = true) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(containerViewId, fragment)
        if (addToBackStack) {
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
