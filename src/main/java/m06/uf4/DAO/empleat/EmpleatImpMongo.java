package m06.uf4.DAO.empleat;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertManyResult;
import com.mongodb.client.result.InsertOneResult;
import m06.uf4.DAOFactory.DAOFactoryMongo;

import java.util.List;

public class EmpleatImpMongo implements EmpleatDAO {
    MongoDatabase mongoDB;
    String coleccio = "empleats";

    public EmpleatImpMongo() {
        mongoDB = DAOFactoryMongo.connectaBD();
    }


    @Override
    public boolean insertar(Empleat emps) {
        return false;
    }

    @Override
    public int insertarLlista(List<Empleat> emps) {
        return 0;
    }

    @Override
    public boolean eliminar(int empId) {
        return false;
    }

    @Override
    public boolean eliminarConjunt() {
        return false;
    }

    @Override
    public boolean modificar(Empleat emp) {
        return false;
    }

    @Override
    public Empleat consultar(int empId) {
        return null;
    }

    @Override
    public List<Empleat> consultarLlista() {
        return null;
    }
}
