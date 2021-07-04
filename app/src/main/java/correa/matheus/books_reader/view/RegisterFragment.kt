package correa.matheus.books_reader.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import correa.matheus.books_reader.R
import correa.matheus.books_reader.viewModel.RegisterViewModel
import org.w3c.dom.Text

class RegisterFragment : Fragment() {

    companion object {
        fun newInstance() = RegisterFragment()
    }

    private lateinit var viewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.register_fragment, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val title = view.findViewById<TextView>(R.id.title)
        val authors = view.findViewById<TextView>(R.id.authors)
        val description = view.findViewById<TextView>(R.id.description)
        val getTitle = arguments?.getString("title")
        val getAuthors = arguments?.getString("authors")
        val getDescription = arguments?.getString("description")

        title.text = getTitle
        authors.text = getAuthors
        description.text = getDescription

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        // TODO: Use the ViewModel
    }

}