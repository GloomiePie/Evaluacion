import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class getData {

    private static void getDataVoto(Connection con) throws SQLException {
        String select = "SELECT COUNT(voto) FROM ASAMBLEISTA WHERE voto= 'S' ";
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(select)
        ) {
            while(rs.next()){
                System.out.printf("%s\n",
                        rs.getString("VOTO"));
            }

        }
    }

    private static void getDataRegion(Connection con) throws SQLException{
        String select = "SELECT COUNT(region) FROM ASAMBLEISTA WHERE region= 'N' ";
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(select)
        ) {
            while(rs.next()){
                System.out.printf("%s\n",
                        rs.getString("REGION"));
            }

        }
    }

}
