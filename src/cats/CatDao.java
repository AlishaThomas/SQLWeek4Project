package cats;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class CatDao {
	private static final String Cats_Table = "cats";

	public void addCat(Cat cat) {
		String sql = "" 
				+ "INSERT INTO " + Cats_Table + " (cat_name) VALUES (?)";
		
		try(Connection conn = DBConnection.getConnection()){
			try(PreparedStatement ps = conn.prepareStatement(sql)){
				ps.setString(1, cat.getCatName());
				
				
				ps.executeUpdate();
			}
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Cat> fetchAllCats() {
		String sql = "SELECT * FROM " + Cats_Table + " ORDER BY cat_name";
		
		try(Connection conn = DBConnection.getConnection()){
			try(PreparedStatement ps = conn.prepareStatement(sql)){
				try (ResultSet rs = ps.executeQuery()){
					List<Cat> catList= new LinkedList<>();
					
				while(rs.next()) {
					Cat cat = new Cat();
					cat.setCatId(rs.getObject("cat_id", Integer.class));
					cat.setCatName(rs.getString("cat_name"));
					
					catList.add(cat);
				}
					
					return catList;
					
				}	
			
			}
			}catch(SQLException e) {
				throw new RuntimeException(e);
				
		}

	}

	public void changeCatName(Cat cat) {
		String sql = "UPDATE " + Cats_Table + " SET cat_name = ? WHERE cat_id = ?";
		
		try(Connection conn = DBConnection.getConnection()){
			try(PreparedStatement ps = conn.prepareStatement(sql)){
				ps.setString(1, cat.getCatName());
				ps.setInt(2, cat.getCatId());
				
				ps.executeUpdate();
			}
		} catch (SQLException e) {
		throw new RuntimeException(e);
		}
		
	}

	public void removeCat(Integer catId) {
		String sql = "DELETE FROM " + Cats_Table + " WHERE cat_id = ?";
		
		try(Connection conn = DBConnection.getConnection()){
			try(PreparedStatement ps = conn.prepareStatement(sql)){
				ps.setInt(1, catId);
				ps.executeUpdate();
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
