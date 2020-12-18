package sg.edu.iss.team8ca.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.team8ca.model.Inventory;
import sg.edu.iss.team8ca.repo.InventoryRepo;

@Service
public class ReorderReportService implements ReorderReportInterface {

	@Autowired
	private InventoryRepo invrepo;
	
	@Transactional
	public void printDatFile() {
		BufferedWriter bw = null;
		{
			try {
				String str = "PartNo" + "Unit Price";
				List<Inventory> invList = invrepo.reorderreport();
				if(invList.size()==0) {
					return;
				}

				File file = new File("C:\\Users\\Administrator\\git\\inventory.dat");
				
				if (!file.exists()) {
					file.createNewFile();
				}

				FileWriter fw = new FileWriter(file);
				bw = new BufferedWriter(fw);
				bw.write("Inventory Report");
				bw.newLine();
				bw.write(str);
				bw.newLine();
				for(Inventory inv : invList) {
					bw.write(inv.toString());
					bw.newLine();
				}
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
