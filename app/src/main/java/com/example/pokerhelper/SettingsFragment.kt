package com.example.pokerhelper

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
import com.example.pokerhelper.databinding.FragmentSettingsBinding
import com.example.pokerhelper.databinding.FragmentSplashBinding
import com.example.pokerhelper.db.Game
import com.example.pokerhelper.db.PokerDataBase

class SettingsFragment : Fragment() {

    companion object {
        const val TAG = "SettingsFragment"

        fun printLog(str: String) {
            Log.d(TAG, str)
        }
    }

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private val dao = PokerDataBase.getDbDao()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etQuantity.doAfterTextChanged {
            if (it.isNullOrBlank()) binding.etQuantity.setText("0")
            else if (it.toString().endsWith('0') && it.length > 1) {
                binding.etQuantity.setText(binding.etQuantity.text.toString().substringBefore('0'))
            } else {
                printLog(it.toString())
                binding.etPlayer1.isVisible = it.toString().toInt() > 0
                binding.etPlayer2.isVisible = it.toString().toInt() > 1
                binding.etPlayer3.isVisible = it.toString().toInt() > 2
                binding.etPlayer4.isVisible = it.toString().toInt() > 3
                binding.etPlayer5.isVisible = it.toString().toInt() > 4
                binding.etPlayer6.isVisible = it.toString().toInt() > 5
            }
        }

        binding.btnStart.setOnClickListener {
            start()
        }
    }

    private fun start() {
        with(binding) {
            val blind = etBlind.text.toString().let { if (it != "") it.toInt() else 0 }
            val game = Game(
                players = listOf(
                    etPlayer1.text.toString(), etPlayer2.text.toString(),
                    etPlayer3.text.toString(), etPlayer4.text.toString(), etPlayer5.text.toString(),
                    etPlayer6.text.toString()
                ), blind = blind
            )
            dao.insert(game)
        }
        findNavController().navigate(R.id.action_settingsFragment_to_mainFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}