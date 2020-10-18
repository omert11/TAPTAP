/*package tap.tap

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

class ArkadasRecadpt (var arkadaslar: ArrayList<arkadasdata>, val Contex: Context, val itemClickListener: (String, String) -> Unit) : RecyclerView.Adapter<ArkadasRecadpt.ArkadasViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ArkadasViewHolder {
        var intt = LayoutInflater.from(p0.context)
        var teksohbet = intt.inflate(R.layout.teksatir_arkadas, p0, false)
        val viewholder = ArkadasViewHolder(teksohbet, p0)
        return viewholder

    }

    override fun getItemCount(): Int {
        return arkadaslar.size
    }

    override fun onBindViewHolder(holder: ArkadasViewHolder, position: Int) {
        holder.durumagoresirala(position)
    }

    inner class ArkadasViewHolder(val Viev: View, var ViewGroup: ViewGroup) : RecyclerView.ViewHolder(Viev) {
        var uidgidecek=""
        var turgidecek=""
        var pos=0
        val teksatirarkadas = Viev as ConstraintLayout
        val teksatirisim = teksatirarkadas.arkadastekisim
        val teksatirdurum = teksatirarkadas.arkadastekdurum
        val teksatirresim = teksatirarkadas.arkadastekresim
        val teksatirbilgi = teksatirarkadas.arkadastekbilgi
        val teksatirekle = teksatirarkadas.arkadastekonayla
        val teksatirred = teksatirarkadas.arkadastekred
        val teksatirmesaj = teksatirarkadas.arkadastekmesajyolla
        fun durumagoresirala(position: Int) {
            val arkadaslarduzekli = ArrayList<arkadasdata>()
            val arkadaslarduzistek = ArrayList<arkadasdata>()
            for (arkadas in arkadaslar) {
                if (arkadas.durumistekarkadas!!) {
                    arkadaslarduzekli.add(arkadas)
                } else {
                    arkadaslarduzistek.add(arkadas)
                }
            }
            arkadaslar.clear()
            arkadaslar.addAll(arkadaslarduzekli)
            arkadaslar.addAll(arkadaslarduzistek)
            durumagoreata(position)
        }

        private fun durumagoreata(position: Int) {
            FirebaseDatabase.getInstance().getReference().child("Kullanicilar").child(arkadaslar[position].uid!!).child("isim")
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {

                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        val isim = p0.getValue() as String
                        teksatirisim.text=isim
                    }

                })
            FirebaseDatabase.getInstance().getReference().child("Kullanicilar").child(arkadaslar[position].uid!!).child("songorulme")
                .addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {

                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        val durum = p0.getValue() as String
                        if (durum=="Cevrim Ici"){
                            teksatirdurum.text=durum
                            teksatirdurum.setTextColor(Color.GREEN)
                        }else{
                            teksatirdurum.text=durum
                            teksatirdurum.setTextColor(Color.RED)
                        }
                    }

                })
            FirebaseStorage.getInstance().getReference().child("ProfilRes").child(arkadaslar[position].uid!!).child("Profil.jpg").downloadUrl
                .addOnCompleteListener {
                    if (it.isSuccessful){
                        fotorafata(it.result)
                    }
                }
            if (arkadaslar[position].durumistekarkadas!!){
                arkadasgorunumu(position)
            }else{
                istekgorunumu(position)
            }


        }

        private fun istekgorunumu(position: Int) {
            teksatirbilgi.visibility=View.VISIBLE
            teksatirekle.visibility=View.VISIBLE
            teksatirred.visibility=View.VISIBLE
            teksatirekle.setOnClickListener { ekle(position) }
            teksatirred.setOnClickListener { reddet(position) }
        }

        private fun ekle(position: Int) {
            val uid = FirebaseAuth.getInstance().currentUser!!.uid
            val uidark=arkadaslar[position].uid!!
            val dataref=FirebaseDatabase.getInstance().getReference()
            dataref.child("Kullanicilar").child(uidark).child("Istekler").child(uid).setValue(true)
            dataref.child("Kullanicilar").child(uid).child("Istekler").child(uidark).setValue(true)
        }

        private fun reddet(position:Int) {
            val uid = FirebaseAuth.getInstance().currentUser!!.uid
            val uidark=arkadaslar[position].uid!!
            val dataref=FirebaseDatabase.getInstance().getReference()
            dataref.child("Kullanicilar").child(uid).child("Istekler").child(uidark).removeValue()
        }

        private fun arkadasgorunumu(position: Int) {
            teksatirmesaj.visibility=View.VISIBLE
            pos=position
            teksatirmesaj.onClick(itemClickListener)
        }

        private fun mesaajagonder(position: Int,event: (String, String) -> Unit) {
            val uid = FirebaseAuth.getInstance().currentUser!!.uid
            val uidark=arkadaslar[position].uid!!
            val dataref=FirebaseDatabase.getInstance().getReference()
            dataref.child("Kullanicilar").child(uidark).child("GidenKonusma")
                .addListenerForSingleValueEvent(object :ValueEventListener{
                    override fun onCancelled(p0: DatabaseError) {

                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        if (p0.exists()){
                            for (uids in p0.children){
                                val kontroluid=uids.key!!
                                if (uid==kontroluid){
                                    mesajayolla("GidenKonusma",uid,uidark,event)
                                }else if (uids.key==p0.children.last().key){
                                    kontrolkonusmavarmi("GelenKonusma",uid,uidark,event)
                                }
                            }

                        }else{
                            kontrolkonusmavarmi("GelenKonusma",uid,uidark,event)
                        }
                    }

                })
        }

        private fun kontrolkonusmavarmi(Tur: String, uid: String, uidark: String, event: (String, String) -> Unit) {
            val dataref=FirebaseDatabase.getInstance().getReference()
            dataref.child("Kullanicilar").child(uidark).child("GelenKonusma")
                .addListenerForSingleValueEvent(object :ValueEventListener{
                    override fun onCancelled(p0: DatabaseError) {

                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        if(p0.child(uid).exists()){
                            mesajayolla("GelenKonusma",uid,uidark,event)
                        }else{
                            olusuryolla(uid,uidark,event)
                        }
                    }
                })
        }

        private fun olusuryolla(uid: String, uidark: String, event: (String, String) -> Unit) {
            FirebaseDatabase.getInstance().getReference().child("Kullanicilar").child(uidark).child("GelenKonusma").child(uid).child("MesajSayisi").setValue("0")
            FirebaseDatabase.getInstance().getReference().child("Kullanicilar").child(uid).child("GidenKonusma").child(uidark).child("MesajSayisi").setValue("0")
            FirebaseDatabase.getInstance().getReference().child("Kullanicilar").child(uid).child("GidenKonusma").child(uidark).child("Durum").child("15").setValue(true)
            FirebaseDatabase.getInstance().getReference().child("Kullanicilar").child(uidark).child("GelenKonusma").child(uid).child("Durum").child("15").setValue(true)
            FirebaseDatabase.getInstance().getReference().child("Kullanicilar").child(uid).child("GidenKonusma").child(uidark).child("Durum").child("40").setValue(true)
            FirebaseDatabase.getInstance().getReference().child("Kullanicilar").child(uidark).child("GelenKonusma").child(uid).child("Durum").child("40").setValue(true)
            FirebaseDatabase.getInstance().getReference().child("Kullanicilar").child(uid).child("GidenKonusma").child(uidark).child("Durum").child("75").setValue(true)
            FirebaseDatabase.getInstance().getReference().child("Kullanicilar").child(uidark).child("GelenKonusma").child(uid).child("Durum").child("75").setValue(true)
            mesajayolla("GelenKonusma",uid,uidark,event)

        }

        private fun mesajayolla(Tur: String,uid:String,uidark:String,event: (String, String) -> Unit) {
            var turters=""
            if (Tur=="GidenKonusma"){
                turters="GelenKonusma"
            }else{
                turters="GidenKonusma"
            }
            uidgidecek=uidark
            turgidecek=turters
            event.invoke(uidgidecek,turgidecek)
        }

        private fun fotorafata(uri:Uri) {
            Picasso.get().load(uri).into(teksatirresim)
        }

        fun <T :ImageView> T.onClick(event: (String,String) -> Unit): T {
            teksatirmesaj.setOnClickListener {
                mesaajagonder(pos,event)

            }
            return this
        }

    }}*/

