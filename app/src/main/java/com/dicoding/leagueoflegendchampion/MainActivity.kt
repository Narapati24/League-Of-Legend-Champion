package com.dicoding.leagueoflegendchampion

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvChampion: RecyclerView
    private val list = ArrayList<Champion>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        rvChampion = findViewById(R.id.rv_champion)
        rvChampion.setHasFixedSize(true)

        list.addAll(getListChampion())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_profile -> {
                val moveIntent = Intent(this@MainActivity, Profile::class.java)
                startActivity(moveIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListChampion(): ArrayList<Champion>{
        val dataName = resources.getStringArray(R.array.data_name)
        val dataAlias = resources.getStringArray(R.array.data_alias)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataRole = resources.getStringArray(R.array.data_role)
        val dataDificulty = resources.getStringArray(R.array.data_dificulty)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val listChampion = ArrayList<Champion>()
        for (i in dataName.indices){
            val champion = Champion(dataName[i], dataAlias[i], dataDescription[i], dataRole[i], dataDificulty[i], dataPhoto[i])
            listChampion.add(champion)
        }
        return listChampion
    }

    private fun showRecyclerList(){
        rvChampion.layoutManager = LinearLayoutManager(this)
        val listChampionAdapter = ListChampionAdapter(list)
        rvChampion.adapter = listChampionAdapter
    }
}