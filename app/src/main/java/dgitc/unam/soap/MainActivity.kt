package dgitc.unam.soap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import dgitc.unam.soap.databinding.ActivityMainBinding
import java.util.concurrent.Executors


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val executor = Executors.newSingleThreadExecutor()
    private val myHandler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener{
            val numOne = binding.parameterOne.text.toString().trim()
            val numTwo = binding.parameterTwo.text.toString().trim()
            getService(numOne, numTwo)
        }

    }

    private fun getService(numOne: String, numTwo: String) {
        executor.execute {
            val response = CallService().callApi(Utils.METHOD_ADD,numOne,numTwo)
            myHandler.post{
                try {
                    binding.resultado.text= response
                }catch (ex:Exception){
                    ex.printStackTrace()
                }
            }
        }
    }
}