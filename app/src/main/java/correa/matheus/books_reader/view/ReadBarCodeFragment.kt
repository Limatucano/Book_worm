package correa.matheus.books_reader.view

import android.Manifest
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.google.zxing.Result
import correa.matheus.books_reader.R
import correa.matheus.books_reader.model.DataBook
import correa.matheus.books_reader.model.api.Book
import correa.matheus.books_reader.model.api.BookRepository
import correa.matheus.books_reader.model.api.BookResult
import correa.matheus.books_reader.model.api.BooksApiResult
import correa.matheus.books_reader.viewModel.ReadBarCodeViewModel
import me.dm7.barcodescanner.zxing.ZXingScannerView
import pub.devrel.easypermissions.EasyPermissions
import pub.devrel.easypermissions.PermissionRequest


class ReadBarCodeFragment : Fragment(), EasyPermissions.PermissionCallbacks,ZXingScannerView.ResultHandler {
    val REQUEST_CODE_CAMERA = 182
    companion object {
        fun newInstance() = ReadBarCodeFragment()
    }

    private lateinit var viewModel: ReadBarCodeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.read_bar_code_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        askCameraPermission()
        val scan = view.findViewById<ZXingScannerView>(R.id.z_xing_scanner)
        //scan.setResultHandler(this)
        //scan.startCamera(0)
    }

    private fun askCameraPermission(){
        EasyPermissions.requestPermissions(
            PermissionRequest.Builder( this, REQUEST_CODE_CAMERA, Manifest.permission.CAMERA )
                .setRationale( "A permissão de uso de câmera é necessária para que o aplicativo funcione." )
                .setPositiveButtonText( "Ok" )
                .setNegativeButtonText( "Cancelar" )
                .build() )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        /* Encaminhando resultados para EasyPermissions API */
        EasyPermissions.onRequestPermissionsResult(
            requestCode,
            permissions,
            grantResults,
            this )
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ReadBarCodeViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        val scan = view?.findViewById<ZXingScannerView>(R.id.z_xing_scanner)
        scan?.setResultHandler(this)
        scan?.startCamera(0)
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        TODO("Not yet implemented")
    }

    override fun handleResult(result: Result?) {
        val scan = view?.findViewById<ZXingScannerView>(R.id.z_xing_scanner)
        val books = BookRepository()
        if (result != null) {
            books.getBook(result.text){status:Int?, bookApiResult: BooksApiResult? ->
                val firstBook: Book?
                bookApiResult?.items.let {
                    firstBook = it?.get(0)?.volumeInfo
                }
                if(firstBook == null){
                    view?.findNavController()?.navigate(R.id.action_readBarCodeFragment_to_registerFragment)
                }else{
                    val bundle = bundleOf("title" to firstBook.title, "authors" to firstBook.authors.joinToString(), "description" to firstBook.description, "thumbnail" to firstBook.imageLinks.thumbnail)
                    view?.findNavController()?.navigate(R.id.action_readBarCodeFragment_to_registerFragment, bundle)
                }
            }
        }
        scan?.stopCamera()


    }

}