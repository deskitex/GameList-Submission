package com.example.gameengine

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gameengine.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val list = ArrayList<Game>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.rvGames.setHasFixedSize(true)

        list.addAll(getListGames())
        showRecyclerList()
    }

    private fun getListGames(): ArrayList<Game> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDesc = resources.getStringArray(R.array.data_desc)
        val dataDev = resources.getStringArray(R.array.data_dev)
        val dataDate = resources.getStringArray(R.array.data_date)
        val dataSteam = resources.getStringArray(R.array.data_steam)
        val dataFeature = resources.getStringArray(R.array.data_features)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataThumb = resources.obtainTypedArray(R.array.data_thumb)
        val listGame = ArrayList<Game>()
        for (i in dataName.indices) {
            val game = Game(dataName[i],dataDev[i], dataDate[i], dataSteam[i], dataFeature[i], dataThumb.getResourceId(i, -1), dataPhoto.getResourceId(i, -1),dataDesc[i])
            listGame.add(game)
        }
        return listGame
    }

    private fun showRecyclerList() {
        binding.rvGames.layoutManager = LinearLayoutManager(this)
        val listGameAdapter = ListGameAdapter(list)
        binding.rvGames.adapter = listGameAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list -> {
                startActivity(Intent(this, AboutActivity::class.java))
            }
        }
            return super.onOptionsItemSelected(item)
    }

}