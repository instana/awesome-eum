package com.instana.android.shared

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.instana.android.utils.ProgressDialogHelper
import java.lang.reflect.ParameterizedType

/**
 * Base class for fragments that use ViewBinding.
 * Provides convenient functions for inflating the view binding, managing the lifecycle, and showing a progress dialog.
 */
abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    private var _binding: VB? = null
    protected val binding: VB
        get() = _binding!!

    /**
     * Inflates the view binding and returns the root view.
     *
     * @param inflater The layout inflater.
     * @param container The view group container.
     * @param savedInstanceState The saved instance state.
     * @return The root view of the fragment.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = inflateBinding(inflater, container)
        return binding.root
    }

    /**
     * Cleans up the view binding and dismisses the progress dialog.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        hideLoader()
    }

    /**
     * Inflates the view binding using reflection and returns the inflated binding object.
     *
     * @param inflater The layout inflater.
     * @param container The view group container.
     * @return The inflated view binding object.
     */
    private fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): VB {
        val viewBindingClass = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<*>
        val inflateMethod = viewBindingClass.getMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
        return inflateMethod.invoke(null, inflater, container, false) as VB
    }

    /**
     * Shows a progress dialog with the message "Loading...".
     */
    protected fun showLoader() {
        ProgressDialogHelper.showDialog(requireContext(),"Loading...")
    }

    /**
     * Hides the progress dialog.
     */
    protected fun hideLoader() {
        ProgressDialogHelper.dismissDialog()
    }

}
