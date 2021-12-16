package com.ibm.banco.BancoREST;

import com.ibm.banco.BancoREST.entities.Cliente;
import com.ibm.banco.BancoREST.entities.Pasion;
import com.ibm.banco.BancoREST.entities.TarjetaPasion;
import com.ibm.banco.BancoREST.services.ClienteDAO;
import com.ibm.banco.BancoREST.services.PasionDAO;
import com.ibm.banco.BancoREST.services.TarjetaDAO;
import com.ibm.banco.BancoREST.services.TarjetaPasionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class Comandos implements CommandLineRunner {

    @Autowired
    private PasionDAO pasionDAO;
    @Autowired
    private ClienteDAO clienteDAO;

    @Autowired
    private TarjetaDAO tarjetaDAO;

    @Autowired
    private TarjetaPasionDAO tarjetaPasionDAO;

    @Override
    public void run(String... args) throws Exception {

       /* Pasion pasion1= new Pasion(null,"Nadar");
        Pasion pasion2= new Pasion(null,"Compras");
        Pasion pasion3= new Pasion(null,"Viajar");
        Pasion pasion4= new Pasion(null,"Correr");
        Pasion pasion5= new Pasion(null,"Estudiar");

        pasionDAO.guardar(pasion1);
        pasionDAO.guardar(pasion2);
        pasionDAO.guardar(pasion3);
        pasionDAO.guardar(pasion4);
        pasionDAO.guardar(pasion5);

        Cliente cliente1= new Cliente(null,"Cliente 2","Apellido 2","213122",new BigDecimal(1500),24);
        Cliente cliente2= new Cliente(null,"Cliente 3","Apellido 3","213125",new BigDecimal(10000),29);
        Cliente cliente3= new Cliente(null,"Cliente 4","Apellido 4","213126",new BigDecimal(12000),28);
        Cliente cliente4= new Cliente(null,"Cliente 5","Apellido 5","213127",new BigDecimal(9000),29);
        Cliente cliente5= new Cliente(null,"Cliente 6","Apellido 6","213128",new BigDecimal(20000),35);
        Cliente cliente6= new Cliente(null,"Cliente 7","Apellido 7","213129",new BigDecimal(15000),26);
        Cliente cliente7= new Cliente(null,"Cliente 8","Apellido 8","213110",new BigDecimal(17000),29);
        Cliente cliente8= new Cliente(null,"Cliente 9","Apellido 9","213111",new BigDecimal(16000),40);

        clienteDAO.guardar(cliente1);
        clienteDAO.guardar(cliente2);
        clienteDAO.guardar(cliente3);
        clienteDAO.guardar(cliente4);
        clienteDAO.guardar(cliente5);
        clienteDAO.guardar(cliente6);
        clienteDAO.guardar(cliente7);
        clienteDAO.guardar(cliente8);

        Tarjeta tarjeta1= new Tarjeta(null,"Tarjeta Gris BBVA", TipoTarjeta.PREMIUM);
        Tarjeta tarjeta2= new Tarjeta(null,"Tarjeta Gold BBVA", TipoTarjeta.GOLD);
        Tarjeta tarjeta3= new Tarjeta(null,"Tarjeta Premiere Banamex", TipoTarjeta.ESTANDAR);
        Tarjeta tarjeta4= new Tarjeta(null,"Tarjeta Like U", TipoTarjeta.PREMIUM);
        Tarjeta tarjeta5= new Tarjeta(null,"Tarjeta Black Santander", TipoTarjeta.PREMIUM);
        Tarjeta tarjeta6= new Tarjeta(null,"Tarjeta Credito HSBC", TipoTarjeta.ESTANDAR);
        Tarjeta tarjeta7= new Tarjeta(null,"Tarjeta Inbursa", TipoTarjeta.PREMIUM);
        Tarjeta tarjeta8= new Tarjeta(null,"Tarjeta AMEX", TipoTarjeta.GOLD);

        tarjetaDAO.guardar(tarjeta1);
        tarjetaDAO.guardar(tarjeta2);
        tarjetaDAO.guardar(tarjeta3);
        tarjetaDAO.guardar(tarjeta4);
        tarjetaDAO.guardar(tarjeta5);
        tarjetaDAO.guardar(tarjeta6);
        tarjetaDAO.guardar(tarjeta7);
        tarjetaDAO.guardar(tarjeta8);
        */

        //Pasion pasion=(Pasion) pasionDAO.buscarPorID(3).get();
        //Tarjeta tarjeta=(Tarjeta) tarjetaDAO.buscarPorID(8).get();

        //TarjetaPasion tarjetaPasion= new TarjetaPasion(null,60,30,50000,16000);
        //System.out.println(tarjetaPasionDAO.guardar(tarjetaPasion).toString());

        /*TarjetaPasion tarjetaPasion1=tarjetaPasionDAO.buscarPorID(8).get();
        tarjetaPasion1.setTarjeta(tarjeta);
        tarjetaPasion1.setPasion(pasion);
        tarjetaPasionDAO.guardar(tarjetaPasion1);*)
         */

       /* List<Tarjeta> tarejtas = (List<Tarjeta>) tarjetaDAO.findTarjetasPorPasionEdadAndSalario(30,10000,"Nadar");
       tarejtas.forEach(System.out::println);



       /*Cliente cliente =clienteDAO.buscarPorID(2).get();
       Pasion pasion=pasionDAO.buscarPorID(2).get();
       cliente.setPasion(pasion);
       clienteDAO.guardar(cliente);*/
        List<TarjetaPasion> pasiones= (List<TarjetaPasion>) tarjetaPasionDAO.buscarTodos();

        pasiones.forEach(System.out::println);

    }
}
