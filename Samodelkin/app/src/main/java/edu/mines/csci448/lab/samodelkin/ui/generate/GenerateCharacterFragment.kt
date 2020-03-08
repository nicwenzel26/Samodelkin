package edu.mines.csci448.lab.samodelkin.ui.generate

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.work.WorkManager
import edu.mines.csci448.lab.samodelkin.R
import edu.mines.csci448.lab.samodelkin.data.Character
import edu.mines.csci448.lab.samodelkin.util.CharacterGenerator

private const val CHARACTER_DATA_BUNDLE_KEY = "448.labs.CHARACTER_DATA_BUNDLE_KEY"

class GenerateCharacterFragment : Fragment() {

    private val logTag = "448.GenCharFrag"

    private var characterData = CharacterGenerator.generate()

    private lateinit var generateCharacterViewModel: GenerateCharacterViewModel

    private lateinit var nameTextView: TextView
    private lateinit var raceTextView: TextView
    private lateinit var strengthTextView: TextView
    private lateinit var wisdomTextView: TextView
    private lateinit var dexterityTextView: TextView

    private lateinit var generateButton: Button
    private lateinit var apiButton: Button
    private lateinit var saveButton: Button

    private lateinit var workManager: WorkManager

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(logTag, "onAttach() called")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(logTag, "onCreate() called")

        characterData = if (savedInstanceState != null) {
            savedInstanceState.getSerializable(CHARACTER_DATA_BUNDLE_KEY) as Character
        } else {
            CharacterGenerator.generate()
        }

        val factory = GenerateCharacterViewModelFactory(requireActivity())
        generateCharacterViewModel = ViewModelProvider(this, factory).get(GenerateCharacterViewModel::class.java)

        workManager = WorkManager.getInstance(requireContext())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(logTag, "onCreateView() called")
        val view = inflater.inflate(R.layout.fragment_new_character, container, false)

        nameTextView = view.findViewById(R.id.name_text_view)
        raceTextView = view.findViewById(R.id.race_text_view)
        strengthTextView = view.findViewById(R.id.strength_text_view)
        wisdomTextView = view.findViewById(R.id.wisdom_text_view)
        dexterityTextView = view.findViewById(R.id.dexterity_text_view)

        generateButton = view.findViewById(R.id.generate_button)
        apiButton = view.findViewById(R.id.api_button)
        saveButton = view.findViewById(R.id.save_button)

        generateButton.setOnClickListener {
            characterData = CharacterGenerator.generate()
            displayCharacterData()
        }

        // TODO Step II.1
        apiButton.isEnabled = false

        saveButton.setOnClickListener {
            generateCharacterViewModel.addCharacter(characterData)
            // TODO return to CharacterListFragment
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(logTag, "onViewCreated() called")

        displayCharacterData()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(CHARACTER_DATA_BUNDLE_KEY, characterData)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(logTag, "onActivityCreated() called")
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

    override fun onDestroyView() {
        Log.d(logTag, "onDestroyView() called")
        super.onDestroyView()

        workManager.cancelAllWork()
    }

    override fun onDestroy() {
        Log.d(logTag, "onDestroy() called")
        super.onDestroy()
    }

    override fun onDetach() {
        Log.d(logTag, "onDetach() called")
        super.onDetach()
    }

    private fun displayCharacterData() {
        characterData.run {
            nameTextView.text = name
            raceTextView.text = race.toLowerCase().capitalize()
            dexterityTextView.text = dex
            wisdomTextView.text = wis
            strengthTextView.text = str
        }
    }

}