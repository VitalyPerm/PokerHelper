package com.example.pokerhelper

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.pokerhelper.databinding.FragmentMainBinding
import com.example.pokerhelper.databinding.FragmentSettingsBinding
import com.example.pokerhelper.db.PokerDataBase


class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val dao = PokerDataBase.getDbDao()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val game = dao.getAll().last()
        with(binding) {
            player1.text = game.players[0]
            player2.text = game.players[1]
            player3.text = game.players[2]
            player4.text = game.players[3]
            player5.text = game.players[4]
            player6.text = game.players[5]
            player1.isVisible = player1.text.isNotEmpty()
            player2.isVisible = player2.text.isNotEmpty()
            player3.isVisible = player3.text.isNotEmpty()
            player4.isVisible = player4.text.isNotEmpty()
            player5.isVisible = player5.text.isNotEmpty()
            player6.isVisible = player6.text.isNotEmpty()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}