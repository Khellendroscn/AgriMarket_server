package com.myeclipseide.ws;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.agri.adapter.ExhibitionAdapter;
import com.agri.bean.impl.ExhibitionImpl;
import com.agri.bean.impl.ProductImpl;
import com.agri.db.DBPoolManager;
import net.khe.db2.DBQuaryException;
import net.khe.db2.DataBase;

@Produces({"application/xml" })
@Path("exhibition")
public class ExhibitionResource {
	private DBPoolManager manager = DBPoolManager.getInstance();
	@GET
	@Produces({"application/xml" })
	public List<Exhibition> getExhibition() {
		List<Exhibition> exhibitions = new ArrayList<Exhibition>();
		DataBase<ProductImpl> dbProduct = manager.newDataBase(ProductImpl.class);
		DataBase<ExhibitionImpl> dbExhibition =
				manager.newDataBase(ExhibitionImpl.class);
		try {
			dbExhibition.query().stream()
					.forEach(exh->{
						try {
							ProductImpl product =
                                    dbProduct.getInstance(exh.getProductId());
							Exhibition exhibition =
									new ExhibitionAdapter(exh,product);
							exhibitions.add(exhibition);
						} catch (DBQuaryException e) {
							e.printStackTrace();
						}
					});
		} catch (DBQuaryException e) {
			e.printStackTrace();
		} finally {
			try {
				dbExhibition.close();
				dbProduct.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return exhibitions;
	}
}
