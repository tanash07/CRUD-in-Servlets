import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmpDao {

	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_projects", "root", "root");
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}

	public static int save(Employee e) {
		int status = 0;
		try {
			Connection con = EmpDao.getConnection();
			PreparedStatement ps = con
					.prepareStatement("insert into user_register(name,password,email,country) values (?,?,?,?)");
			ps.setString(1, e.getName());
			ps.setString(2, e.getPassword());
			ps.setString(3, e.getEmail());
			ps.setString(4, e.getCounrty());

			status = ps.executeUpdate();

			// con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return status;
	}

	public static int update(Employee employee) {
		int status = 0;
		try {
			Connection con = EmpDao.getConnection();
			PreparedStatement ps = con
					.prepareStatement("update user_register set name =?,password =?,email =?,country = ? where id =?");
			ps.setString(1, employee.getName());
			ps.setString(2, employee.getPassword());
			ps.setString(3, employee.getEmail());
			ps.setString(4, employee.getCounrty());
			ps.setInt(5, employee.getId());

			status = ps.executeUpdate();

//			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}

	public static int delete(int id) {
		int status = 0;

		try {
			Connection con = EmpDao.getConnection();

			PreparedStatement ps = con.prepareStatement("delete from user_register where id =?");
			ps.setInt(1, id);

			status = ps.executeUpdate();
			// con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;

	}

	public static Employee getEmployeeById(int id) {
		Employee employee = new Employee();

		try {
			Connection con = EmpDao.getConnection();

			PreparedStatement ps = con.prepareStatement("select * from user where id =?");
			ps.setInt(1, employee.getId());

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				employee.setId(rs.getInt(1));
				employee.setName(rs.getString(2));
				employee.setPassword(rs.getString(3));
				employee.setEmail(rs.getString(4));
				employee.setCounrty(rs.getString(5));

			}

//			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return employee;

	}

	public static List<Employee> getAllEmployees() {
		List<Employee> list = new ArrayList<Employee>();
		try {
			Connection con = EmpDao.getConnection();

			PreparedStatement ps = con.prepareStatement("select * from user_register");

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Employee employee = new Employee();
				employee.setId(rs.getInt(1));
				employee.setName(rs.getString(2));
				employee.setPassword(rs.getString(3));
				employee.setEmail(rs.getString(4));
				employee.setCounrty(rs.getString(5));
				list.add(employee);

			}
//			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
