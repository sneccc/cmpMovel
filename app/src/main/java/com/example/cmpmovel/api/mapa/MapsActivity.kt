package com.example.cmpmovel.api.mapa

import android.Manifest.permission
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.cmpmovel.R
import com.example.cmpmovel.api.*
import com.example.cmpmovel.createNota
import com.google.android.gms.location.*
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private val LOCATION_PERMISSION_REQUEST_CODE = 1
    private lateinit var mMap: GoogleMap
    private lateinit var marcadores: List<Marcador>
    private lateinit var lastlocation: Location
    private lateinit var fusedLocationCliente: FusedLocationProviderClient
    private lateinit var locationRequest:LocationRequest
    private lateinit var locationCallback:LocationCallback
    private val EditMarcadorCode = 4
    private val ElimnarMarcadorCode = 5
    private val CriarMarcadorCode =6
    private lateinit var localizacaoAtual:LatLng
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        //initialize fusedlocationCliente -------------
        fusedLocationCliente = LocationServices.getFusedLocationProviderClient(this)

        locationCallback =object :LocationCallback(){
            override fun onLocationResult(p0: LocationResult) {
                super.onLocationResult(p0)
                lastlocation=p0.lastLocation
                var loc =LatLng(lastlocation.latitude,lastlocation.longitude)
               // mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc,15.0f))
                localizacaoAtual=loc
            }
        }
    preencherMarcadores()

        createLocationRequest()
    }

    private fun createLocationRequest() {
        locationRequest=LocationRequest()
        //Intervalo que app vai receber updates
        locationRequest.interval=10000
        locationRequest.priority=LocationRequest.PRIORITY_HIGH_ACCURACY
    }

    override fun onPause() {
        super.onPause()
        fusedLocationCliente.removeLocationUpdates(locationCallback)
    }

    override fun onResume() {
        super.onResume()
        startLocationUpdates()//Começar a receber updates
    }

    private fun startLocationUpdates() {//Verificar se existem permissões
        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),LOCATION_PERMISSION_REQUEST_CODE)
            return
        }
        fusedLocationCliente.requestLocationUpdates(locationRequest,locationCallback,null/*looper*/)
    }

    private fun preencherMarcadores() {
        //Adicionar Marcadores dos endpoints
        val request = ServiceBuilder.buildService(EndPoints::class.java)
        val call = request.getMarcadores()
        var position: LatLng

        call.enqueue(object : Callback<List<Marcador>> {


            override fun onResponse(
                call: Call<List<Marcador>>,
                response: Response<List<Marcador>>
            ) {
                if (response.isSuccessful) {
                    marcadores = response.body()!!
                    for (marcador in marcadores) {
                        position = LatLng(
                            marcador.lat_marcador.toString().toDouble(),
                            marcador.lon_marcador.toString().toDouble()
                        )
                        var esteMarcador = mMap.addMarker(
                            MarkerOptions().position(position)
                                .title("${marcador.titulo_marcador} ID:${marcador.id}")
                        )
                        esteMarcador.tag = 0 //Para fins de click
                    }
                }
            }

            override fun onFailure(call: Call<List<Marcador>>, t: Throwable) {
               // Toast.makeText(this@MapsActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        //    val sydney = LatLng(-34.0, 151.0)
        //  mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        val sydney = LatLng(-34.0, 151.0)
        var novomarcador = mMap.addMarker(
            MarkerOptions()
                .position(sydney)
                .title("Sydney")
        )
        //var data= arrayOf(0,1)

        novomarcador.tag = 0

        setupMap()

        mMap.setOnMarkerClickListener(this)
    }


    private fun setupMap() {
        if (ActivityCompat.checkSelfPermission(
                this,
                permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
            return
        } else {
            //1 Mostra o pontinho azul
            mMap.isMyLocationEnabled = true


            //2
            fusedLocationCliente.lastLocation.addOnSuccessListener(this) { location ->
                if (location != null) {//Buscar a localização e centrar o mapa na posição atual
                    lastlocation = location
                  //  Toast.makeText(this@MapsActivity, lastlocation.toString(), Toast.LENGTH_SHORT).show()

                    val currentLatLng = LatLng(location.latitude, location.longitude)
                    var meuMarcador = mMap.addMarker(
                        MarkerOptions().position(currentLatLng)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                            .title("Marcador ID:10").snippet("Um snipet")
                    )

                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12f))
                }
            }


        }
    }

    override fun onMarkerClick(marker: Marker): Boolean {

        // Quando marcador não tem tag criamos
        if (marker.tag == null) {
          //  Toast.makeText(this, "Não tem Tag", Toast.LENGTH_SHORT).show()
            marker.tag = 0
        } else {//Verificamos o duplo click
            var clickCount = marker.tag as Int
            var newClickCount = clickCount + 1


            //Duplo Click
            if (newClickCount == 2) {
                Toast.makeText(this, "Duplo Click", Toast.LENGTH_SHORT).show()
                newClickCount = 0
                abrirMarcador(marker)

            }
            if (newClickCount > 2) {
                newClickCount = 0
            }
            marker.tag = newClickCount
        }
        return false
    }

    private fun abrirMarcador(marker: Marker) {
        val split = marker.title.split(':')
        val ID_Marcador = split[1].toInt()

        //.makeText(this, "Numero ${ID_Marcador}", Toast.LENGTH_SHORT).show()

        Log.d("consoleTAG", "Marcador ID-->" + ID_Marcador)
        //Buscar informações deste Marcador pelo ID
        val request = ServiceBuilder.buildService(EndPoints::class.java)
        val call =
            request.getMarcadoresByID(ID_Marcador)//Pelo ID_Marcador sabemos qual o id deste Marcador para depois chamar-mos o endpoint de ir buscar informações pelo id
        var position: LatLng
        call.enqueue(object : Callback<List<Marcador>> {


            override fun onResponse(
                call: Call<List<Marcador>>,
                response: Response<List<Marcador>>
            ) {
                if (response.isSuccessful) {
                    marcadores = response.body()!!

                    Log.d("consoleTAG", "Request Marcador Sucesso ")
                    //Edit Intent
                    editIntent(marcadores)

                }
            }

            override fun onFailure(call: Call<List<Marcador>>, t: Throwable) {
                Log.d("consoleTAG", "Request Marcador Falhou ")
              //  Toast.makeText(this@MapsActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })


    }

    //Abrir uma nova atividade para editar o marcador editado
    private fun editIntent(marcadores: List<Marcador>) {
        for (marcador in marcadores) {

            //Pelo Resultado deste deste endpoint preencher o Edit que corresponde a  este marcador
            val id = marcador.id
            val titulo = marcador.titulo_marcador
            val descricao = marcador.desc_marcador
            val lat = marcador.lat_marcador.toString()
            val lon = marcador.lon_marcador.toString()

            Log.d("consoleTAG", "Request Marcador DATA -> $marcador")
            val intent = Intent(this, EditarMarcador::class.java).apply {
                putExtra(MapsActivity.EXTRA_ID, id)
                putExtra(MapsActivity.EXTRA_TITULO, titulo)
                putExtra(MapsActivity.EXTRA_DESCRICAO, descricao)
                putExtra(MapsActivity.EXTRA_LAT, lat)
                putExtra(MapsActivity.EXTRA_LON, lon)
            }
            startActivityForResult(intent, EditMarcadorCode)

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("consoleTAG", "RequestCode->$requestCode")
        if (requestCode == EditMarcadorCode) { //EDITAR MARCADOR
            if (resultCode == Activity.RESULT_OK) {

                //Obter informações que editamos
                val id = data!!.getStringExtra(MapsActivity.EXTRA_ID)
                val title_editado = data.getStringExtra(MapsActivity.EXTRA_TITULO)
                val desc_editado = data.getStringExtra(MapsActivity.EXTRA_DESCRICAO)
                val lat_editado = data.getStringExtra(MapsActivity.EXTRA_LAT).toDouble()
                val lon_editado = data.getStringExtra(MapsActivity.EXTRA_LON).toDouble()
                Log.d("consoleTAG", "Marcadores Variaveis de Chegada" +id+title_editado+desc_editado+lat_editado+lon_editado)

                if (id != null && title_editado != null && desc_editado != null && lat_editado != null && lon_editado != null) { //Se nenhum dado for nulo

                    //Retrofit
                    val request = ServiceBuilder.buildService(EndPoints::class.java)    //Instancia do nosso serviço , que permite fazer chamadas http
                    val call = request.editMarcador(id.toInt(),desc_editado,title_editado,lat_editado,lon_editado)
                    call.enqueue(object : Callback<Marcador> {
                        override fun onResponse(call: Call<Marcador>, response: Response<Marcador>) {

                            if (response.isSuccessful) {
                                Log.d("consoleTAG", "Sucesso Marcador Editado" +response.body()!!)
                                mMap.clear()//Ajuda a resetar o mapa para atualizar os marcadores
                                preencherMarcadores() //Preenche os marcadores no mapa denovo
                            }
                        }

                        override fun onFailure(call: Call<Marcador>, t: Throwable) {
                           // Toast.makeText(this@MapsActivity, "${t.message}", Toast.LENGTH_LONG).show()
                            Log.d("consoleTAG", "Falhou editar->" + t.message.toString() +call.toString()+t.stackTrace)
                        }

                    })

                }
            }else if(resultCode==ElimnarMarcadorCode){
                val id = data!!.getStringExtra(MapsActivity.EXTRA_ID)
                if (id!=null){
                    val request = ServiceBuilder.buildService(EndPoints::class.java)//Instancia do nosso serviço , que permite fazer chamadas http
                    val call = request.deleteMarcador(id.toInt())

                    call.enqueue(object : Callback<Marcador> {
                        override fun onResponse(call: Call<Marcador>, response: Response<Marcador>) {

                            if (response.isSuccessful) {
                                Log.d("consoleTAG", "Sucesso DELETE MARCADOR")
                                mMap.clear()//Ajuda a resetar o mapa para atualizar os marcadores
                                preencherMarcadores() //Preenche os marcadores no mapa denovo
                            }
                        }

                        override fun onFailure(call: Call<Marcador>, t: Throwable) {
                         //   Toast.makeText(this@MapsActivity, "${t.message}", Toast.LENGTH_LONG).show()
                            Log.d("consoleTAG", "Falhou DELETAR MARCADOR->" + t.message)
                        }

                    })


            }}

        }else if(requestCode == CriarMarcadorCode){
            if (resultCode == Activity.RESULT_OK){
                val title = data!!.getStringExtra(MapsActivity.EXTRA_TITULO)
                val descricao = data!!.getStringExtra(MapsActivity.EXTRA_DESCRICAO)
                //Obter coordenadas atuais
                val lat=localizacaoAtual.latitude
                val lon=localizacaoAtual.longitude
                if (title != null && descricao != null && lat!=null && lon!=null ) {
                    Log.d("consoleTAG", "Variavies Cirar Marcador $title, $descricao, $lat, $lon")

                    //Retrofit
                    val request =ServiceBuilder.buildService(EndPoints::class.java)     //Instancia do nosso serviço , que permite fazer chamadas http
                    val call = request.postMarcador(title,descricao,lat,lon)                          //Chamar o endpoint postTest com parametros

                    call.enqueue(object : Callback<Marcador> {

                        override fun onResponse(call: Call<Marcador>, response: Response<Marcador>) {
                            if (response.isSuccessful) {
                                Log.d("consoleTAG", "Sucesso Criar Marcador -> "+response)
                                mMap.clear()//Ajuda a resetar o mapa para atualizar os marcadores
                                preencherMarcadores() //Preenche os marcadores no mapa denovo
                            }
                        }

                        override fun onFailure(call: Call<Marcador>, t: Throwable) {
                            Log.d("consoleTAG", "Falhou-> ${t.stackTrace}   ${t.cause}    ${t.localizedMessage}")
                        }

                    })

                }

            }


        }

    }

    fun criarMarcador(view: View) {



        val intent = Intent(this@MapsActivity, CriarMarcador::class.java)
        startActivityForResult(intent, CriarMarcadorCode)



    }
    companion object {
        const val EXTRA_TITULO = "titulo"
        const val EXTRA_DESCRICAO = "descricao"
        const val EXTRA_ID = "id"
        const val EXTRA_LAT = "lat"
        const val EXTRA_LON = "lon"
    }


}