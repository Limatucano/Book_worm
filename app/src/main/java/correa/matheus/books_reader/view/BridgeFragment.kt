package correa.matheus.books_reader.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import correa.matheus.books_reader.R
import correa.matheus.books_reader.model.DataBook
import correa.matheus.books_reader.model.api.BookRepository
import correa.matheus.books_reader.model.api.BookResult
import correa.matheus.books_reader.model.api.BooksApiResult
import correa.matheus.books_reader.viewModel.BridgeViewModel

class BridgeFragment : Fragment() {

    companion object {
        fun newInstance() = BridgeFragment()
    }

    private lateinit var viewModel: BridgeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bridge_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val btn_scan = view.findViewById<Button>(R.id.btn_scan)

        btn_scan.setOnClickListener {
            view.findNavController().navigate(R.id.action_bridgeFragment_to_readBarCodeFragment)
        }
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BridgeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}