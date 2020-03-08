package edu.mines.csci448.lab.samodelkin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import edu.mines.csci448.lab.samodelkin.R
import edu.mines.csci448.lab.samodelkin.ui.detail.CharacterDetailFragment
import edu.mines.csci448.lab.samodelkin.ui.list.CharacterListFragment
import java.util.*

class MainActivity : AppCompatActivity(),
    CharacterListFragment.Callbacks {

    private val logTag = "448.MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(logTag, "onCreate() called")
        setContentView(R.layout.activity_main)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

        if(currentFragment == null) {
            val listFragment = CharacterListFragment()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, listFragment)
                .commit()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(logTag, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(logTag, "onResume() called")
    }

    override fun onPause() {
        Log.d(logTag, "onPause() called")
        super.onPause()
    }

    override fun onStop() {
        Log.d(logTag, "onStop() called")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(logTag, "onDestroy() called")
        super.onDestroy()
    }

    override fun onCharacterSelected(characterId: UUID) {
        Log.d(logTag, "onCharacterSelected() called")
        val fragment = CharacterDetailFragment.newInstance(characterId)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

}
