package dgitc.unam.soap

import org.ksoap2.SoapEnvelope
import org.ksoap2.serialization.SoapObject
import org.ksoap2.serialization.SoapSerializationEnvelope
import org.ksoap2.transport.HttpTransportSE

class CallService {
    fun callApi(methodName: String, numOne:String, numTwo:String):String{
        var result=""
        val SOAP_ACTION = Utils.SOAP_NAMESPACE+methodName
        val soapObject = SoapObject(Utils.SOAP_NAMESPACE,methodName)
        soapObject.addProperty("intA",numOne)
        soapObject.addProperty("intB",numTwo)
        val envelope= SoapSerializationEnvelope(SoapEnvelope.VER11)
        envelope.setOutputSoapObject(soapObject)
        envelope.dotNet = true
        val httpTransportSE = HttpTransportSE(Utils.SOAP_URL)

        try{
            httpTransportSE.call(SOAP_ACTION,envelope)
            val soapPrimitive=  envelope.response
            result = soapPrimitive.toString()
        }catch(ex: Exception){
            ex.printStackTrace()
        }
        return result
    }
}