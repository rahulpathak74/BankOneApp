package com.example.bankingoneapp.ui.main.view.fragments.loyality

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bankingoneapp.databinding.FragmentLoyalityBinding

class LoyalityFragment : Fragment() {

private var _binding: FragmentLoyalityBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val loyalityViewModel =
            ViewModelProvider(this).get(LoyalityViewModel::class.java)

    _binding = FragmentLoyalityBinding.inflate(inflater, container, false)
    val root: View = binding.root

    val textView: TextView = binding.textNotifications
    loyalityViewModel.text.observe(viewLifecycleOwner) {
      textView.text = it
    }
    return root
  }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}