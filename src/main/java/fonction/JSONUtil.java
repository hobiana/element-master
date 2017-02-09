/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fonction;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author ITU
 */
public class JSONUtil {
    public static JSONArray convert( ResultSet rs )throws Exception
    {
      JSONArray json = new JSONArray();
      ResultSetMetaData rsmd = rs.getMetaData();

      while(rs.next()) {
        int numColumns = rsmd.getColumnCount();
        JSONObject obj = new JSONObject();

        for (int i=1; i<numColumns+1; i++) {
          String column_name = rsmd.getColumnName(i);

            switch (rsmd.getColumnType(i)) {
                case java.sql.Types.ARRAY:
                    obj.put(column_name, rs.getArray(column_name));
                    break;
                case java.sql.Types.BIGINT:
                    obj.put(column_name, rs.getInt(column_name));
                    break;
                case java.sql.Types.BOOLEAN:
                    obj.put(column_name, rs.getBoolean(column_name));
                    break;
                case java.sql.Types.BLOB:
                    obj.put(column_name, rs.getBlob(column_name));
                    break;
                case java.sql.Types.DOUBLE:
                    obj.put(column_name, rs.getDouble(column_name));
                    break;
                case java.sql.Types.FLOAT:
                    obj.put(column_name, rs.getFloat(column_name));
                    break;
                case java.sql.Types.INTEGER:
                    obj.put(column_name, rs.getInt(column_name));
                    break;
                case java.sql.Types.NVARCHAR:
                    obj.put(column_name, rs.getNString(column_name));
                    break;
                case java.sql.Types.VARCHAR:
                    obj.put(column_name, rs.getString(column_name));
                    break;
                case java.sql.Types.TINYINT:
                    obj.put(column_name, rs.getInt(column_name));
                    break;
                case java.sql.Types.SMALLINT:
                    obj.put(column_name, rs.getInt(column_name));
                    break;
                case java.sql.Types.DATE:
                    obj.put(column_name, rs.getDate(column_name));
                    break;
                case java.sql.Types.TIMESTAMP:
                    obj.put(column_name, rs.getTimestamp(column_name));
                    break;
                default:
                    obj.put(column_name, rs.getObject(column_name));
                    break;
            }
        }

        json.put(obj);
      }

      return json;
    }
    
    //SPECIAL DEFIS
    public static JSONArray convert( ResultSet rs ,String level)throws Exception
    {
      JSONArray json = new JSONArray();
      ResultSetMetaData rsmd = rs.getMetaData();

      while(rs.next()) {
        int numColumns = rsmd.getColumnCount();
        JSONObject obj = new JSONObject();

        for (int i=1; i<numColumns+1; i++) {
          String column_name = rsmd.getColumnName(i);

            switch (rsmd.getColumnType(i)) {
                case java.sql.Types.ARRAY:
                    obj.put(column_name, rs.getArray(column_name));
                    break;
                case java.sql.Types.BIGINT:
                    obj.put(column_name, rs.getInt(column_name));
                    break;
                case java.sql.Types.BOOLEAN:
                    obj.put(column_name, rs.getBoolean(column_name));
                    break;
                case java.sql.Types.BLOB:
                    obj.put(column_name, rs.getBlob(column_name));
                    break;
                case java.sql.Types.DOUBLE:
                    obj.put(column_name, rs.getDouble(column_name));
                    break;
                case java.sql.Types.FLOAT:
                    obj.put(column_name, rs.getFloat(column_name));
                    break;
                case java.sql.Types.INTEGER:
                    obj.put(column_name, rs.getInt(column_name));
                    break;
                case java.sql.Types.NVARCHAR:
                    obj.put(column_name, rs.getNString(column_name));
                    break;
                case java.sql.Types.VARCHAR:
                    obj.put(column_name, rs.getString(column_name));
                    break;
                case java.sql.Types.TINYINT:
                    obj.put(column_name, rs.getInt(column_name));
                    break;
                case java.sql.Types.SMALLINT:
                    obj.put(column_name, rs.getInt(column_name));
                    break;
                case java.sql.Types.DATE:
                    obj.put(column_name, rs.getDate(column_name));
                    break;
                case java.sql.Types.TIMESTAMP:
                    obj.put(column_name, rs.getTimestamp(column_name));
                    break;
                default:
                    obj.put(column_name, rs.getObject(column_name));
                    break;
            }
        }
        obj.put("level", level);
        json.put(obj);
      }

      return json;
    }
}
