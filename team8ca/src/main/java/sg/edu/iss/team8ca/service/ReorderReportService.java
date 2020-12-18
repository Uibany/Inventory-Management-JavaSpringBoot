package sg.edu.iss.team8ca.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import sg.edu.iss.team8ca.model.Inventory;
import sg.edu.iss.team8ca.repo.InventoryRepo;

@Service
public class ReorderReportService {

	private InventoryRepo invrepo;

	public List<Inventory> reoderReport() {
		return invrepo.reorderreport();
	}

	public void printDatFile(List<Inventory> invList) {
		BufferedWriter bw = null;
		{
			try {
			//	String mycontent = "This String would be written";
				File file = new File("C:/inventory.dat");
				
				if (!file.exists()) {
					file.createNewFile();
				}

				FileWriter fw = new FileWriter(file);
				bw = new BufferedWriter(fw);
				for(Inventory inv : invList) {
				  bw.write(inv.toString());
				}
			//	bw.write(mycontent);
				System.out.println("File written Successfully");

			} catch (IOException ioe) {
				ioe.printStackTrace();
			} finally {
				try {
					if (bw != null)
						bw.close();
				} catch (Exception ex) {
					System.out.println("Error in closing the BufferedWriter" + ex);
				}
			}
		}
	}
}
