	import javax.swing.JFrame;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import javax.swing.*;
	
	public class Swing   {
		public void function(MLL list) {
			//new line
			JFrame f = new JFrame("Pencere");
			JLabel l1 = new JLabel("Category");
			JLabel l2 = new JLabel("Name");
			JLabel l3 = new JLabel("Barcode");
			JLabel l4 = new JLabel("Stock");
			JLabel l5 = new JLabel("Waiting for the edit...");
			JButton btn = new JButton("EDIT!");
			JButton btn1 = new JButton("Menu");
			JButton btn2 = new JButton("Search Category!");//This button helps comboBox to work! Try to find a way to not to use it!
			JTextField txf = null;
			JTextField txfN = null;
			JTextField txfB = null;
			JTextField txfS = null;
			JComboBox cb;
			String[] categories = new String[list.columnSize()];
			ColumnNode tempN = list.getHead();
			int i=0;
			while(tempN!=null) {
				categories[i]=(String) tempN.getData();
				i++;
				if(tempN.getDown()!=null) {
					tempN = tempN.getDown();
				}
				else break;
			}
			
			for(int a=0;a<categories.length;a++) {// Avoids to show  the same category more then once.
				int lastIndex = categories.length-1;
				for(int b =a+1;b<categories.length;b++) {
					if(categories[a] !=null && categories[b] !=null && categories[a].toLowerCase().equals(categories[b].toLowerCase())) {
						while(categories[lastIndex]==null) {
							lastIndex--;
						}
						categories[a]=categories[lastIndex];
						categories[lastIndex]=null;
						b=a+1;
					}
				}
			}
			cb= new JComboBox(categories);
			int count=0;
			 i=-1;
			ColumnNode node = list.getHead();
			while (true) {
				i++;
				
				if(node!= null && i== 0) {
					count = list.elementSize((String)node.getData());
				}
				else if(node!= null && node.getDown()!=null) {
					node = node.getDown();
					
				count += list.elementSize((String)node.getData());
				}
				else if(node.getDown()==null) {
					break;
				}
				
				
			}
			JTextField arrT[][] = new JTextField[count][4];//if user will be edit the categories wich will be added, you can use a variable instead of 4.
			
			i=0;
			ColumnNode head = list.getHead();
			ColumnNode temp = head;
			while(true) {
				
				ElementNode tempE;
				tempE=temp.getRight();
				while(tempE!=null) {
					i++;
					txf = new JTextField(((Product)tempE.getData()).getCategory());//For each part of product will have this but right now i will try with just name.
					txfN= new JTextField(((Product)tempE.getData()).getName());
				    txfB = new JTextField(String.valueOf(((Product)tempE.getData()).getBarcode()));
					txfS = new JTextField(String.valueOf(((Product)tempE.getData()).getStock()));
					txfN.setBounds(110,10+(30*i),100,25);
					txfB.setBounds(210,10+(30*i),100,25);
					txfS.setBounds(310,10+(30*i),100,25);
					txf.setBounds(10,10+(30*i),100,25);
					arrT[i-1][0]=txf;
					arrT[i-1][1]=txfN;
					arrT[i-1][2]=txfB;
					arrT[i-1][3]=txfS;
					f.add(txf);
					f.add(txfN);
					f.add(txfB);
					f.add(txfS);
					
					if(tempE.getNext()!=null) {
						tempE= tempE.getNext();
					}
					else break;
					
				}
				if(temp.getDown()!=null) {
					temp = temp.getDown();
				}
				else break;
				
				
				
			}
			
			
			//l1.setBounds(14, 5, 70, 30);
			l2.setBounds(150,5,70,30);
			l3.setBounds(235,5,70,30);
			l4.setBounds(345,5,70,30);
			l5.setBounds(350, 250, 250, 30);
			
			btn1.setBounds(450, 130, 150, 30);
			btn.setBounds(450, 100, 150, 30);
			btn2.setBounds(450, 160, 150, 30);
			cb.setBounds(10, 10, 100, 20);
			
			
			
			btn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					list.deleteAllElements();
					for (int i=0;i<arrT.length;i++) {
						Product product = new Product();
						String category = arrT[i][0].getText();
						String name = arrT[i][1].getText();
						String barcode = arrT[i][2].getText();
						String stock = arrT[i][3].getText();
						
						arrT[i][0].setText(category);
						arrT[i][1].setText(name);
						arrT[i][2].setText(barcode);
						arrT[i][3].setText(stock);
						product.setCategory(category);
						product.setName(name);
						product.setBarcode(Integer.parseInt(barcode));
						product.setStock(Integer.parseInt(stock));
						list.addElement(product.getCategory(), product);
						
						l5.setText("Succesfull!");
						
						//Þu an kaydediliyor linked liste bu alanda yapýlmasý gereken Daimi depolamaya kaydetmek kaldý!
					}
					f.dispose();
					f.setVisible(false);
					function(list);
					
				}
			});
			btn1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					menu(list);
					f.setVisible(false);
					f.dispose();
					
					
				}
			});
			btn2.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					SingleLinkedList listShown= new SingleLinkedList();
					for(int i=0;i<arrT.length;i++) {
						
						if(arrT[i][0].getText().equals(cb.getSelectedItem())){
							Product p = new Product();
							
							p.setCategory(arrT[i][0].getText());
							p.setName(arrT[i][1].getText());
							p.setBarcode(Integer.parseInt(arrT[i][2].getText()));
							p.setStock(Integer.parseInt(arrT[i][3].getText()));
							listShown.add(p);
									
						}
						
						
					}
					function(list,listShown,((Product)listShown.getHead().getData()).getCategory());
					f.setVisible(false);
					f.dispose();
					
					
				}
			});
			//JScrollBar scr = new JScrollBar();
			//scr.setBounds(770, 0, 30, 750);
			f.add(cb);
			f.add(btn);
			f.add(btn1);
			f.add(btn2);
			f.add(l1);
			f.add(l2);
			f.add(l3);
			f.add(l4);
			f.add(l5);
			f.setSize(800,750);
			f.setLayout(null);
			f.setVisible(true);
			
			
		}
		public void function(MLL list ,SingleLinkedList listshown,String category) {
			//To search category
			
			JFrame f = new JFrame("Pencere");//Arranging buttons and layers
			JLabel l1 = new JLabel("Category");
			JLabel l2 = new JLabel("Name");
			JLabel l3 = new JLabel("Barcode");
			JLabel l4 = new JLabel("Stock");
			JLabel l5 = new JLabel("Waiting for the edit...");
			JButton btn = new JButton("EDIT!");
			JButton btn1 = new JButton("Menu");
			JTextField txf = null;
			JTextField txfN = null;
			JTextField txfB = null;
			JTextField txfS = null;
			JComboBox cb;
			String currentCategory=category;
			int i=0;
			ElementNode temp = listshown.getHead();
			String[] categories = new String[1];
			categories[0]=((Product)temp.getData()).getCategory();
			cb= new JComboBox(categories);
			JTextField arrT[][] = new JTextField[listshown.size()][4];
			
			
			ColumnNode head = list.getHead();
			i=0;
			temp = listshown.getHead();
			
				while(temp!=null) {
					i++;
					txf = new JTextField(((Product)temp.getData()).getCategory());//For each part of product will have this but right now i will try with just name.
					txfN= new JTextField(((Product)temp.getData()).getName());
				    txfB = new JTextField(String.valueOf(((Product)temp.getData()).getBarcode()));
					txfS = new JTextField(String.valueOf(((Product)temp.getData()).getStock()));
					txfN.setBounds(110,10+(30*i),100,25);
					txfB.setBounds(210,10+(30*i),100,25);
					txfS.setBounds(310,10+(30*i),100,25);
					txf.setBounds(10,10+(30*i),100,25);
					arrT[i-1][0]=txf;
					arrT[i-1][1]=txfN;
					arrT[i-1][2]=txfB;
					arrT[i-1][3]=txfS;
					f.add(txf);
					f.add(txfN);
					f.add(txfB);
					f.add(txfS);
					
					if(temp.getNext()!=null) {
						temp= temp.getNext();
					}
					else break;
					
				}
				
				

			temp = listshown.getHead();
			ColumnNode tempC = list.getHead();
			ColumnNode tempCpre = null;
			while(tempC.getDown()!=null && !(tempC.getData().equals(currentCategory))){//searching the column which is our current categories.
				
				tempCpre=tempC;
				tempC=tempC.getDown();
	
			}
			
			if(tempCpre==null) {// if the category column is the head
				
				ColumnNode node = new ColumnNode(((Product)temp.getData()).getCategory());
				node.setData(((Product)temp.getData()).getCategory());
				node.setRight(temp);
				list.headChanger(node);
				
				
				
				
			}
			else if(tempC!=null && temp != null ){
				temp=listshown.getHead();
				ColumnNode node = new ColumnNode(((Product)temp.getData()).getCategory());
				node.setRight(temp);
				node.setDown(tempC.getDown());
				node.setData(((Product)temp.getData()).getCategory());
				tempCpre.setDown(node);
			
			}
			else System.out.println("HATA");
			
			l2.setBounds(150,5,70,30);
			l3.setBounds(235,5,70,30);
			l4.setBounds(345,5,70,30);
			l5.setBounds(350, 250, 250, 30);
			
			btn1.setBounds(450, 130, 150, 30);
			btn.setBounds(450, 100, 150, 30);
			cb.setBounds(10, 10, 100, 20);
			
			//BUTONLARI AYARLA!
			
			btn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					listshown.deleteAllElements();
					for (int i=0;i<arrT.length;i++) {
						Product product = new Product();
						String category = arrT[i][0].getText();
						String name = arrT[i][1].getText();
						String barcode = arrT[i][2].getText();
						String stock = arrT[i][3].getText();
						
						arrT[i][0].setText(category);
						arrT[i][1].setText(name);
						arrT[i][2].setText(barcode);
						arrT[i][3].setText(stock);
						product.setCategory(category);
						product.setName(name);
						product.setBarcode(Integer.parseInt(barcode));
						product.setStock(Integer.parseInt(stock));
						if(currentCategory.equals(arrT[i][0].getText()))
						listshown.add(product);
						else
							list.addElement(product.getCategory(), product);
						
						
						l5.setText("Succesfull!");
						
						//Þu an kaydediliyor linked liste bu alanda yapýlmasý gereken Daimi depolamaya kaydetmek kaldý!
					}
					function(list,listshown,category);
					f.setVisible(false);
					f.dispose();
					
					
				}
			});
			btn1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					f.setVisible(false);
					f.dispose();
					menu(list);
				}
			});
			
			
			f.add(cb);
			f.add(btn);
			f.add(btn1);
			f.add(l1);
			f.add(l2);
			f.add(l3);
			f.add(l4);
			f.add(l5);
			f.setSize(800,750);
			f.setLayout(null);
			f.setVisible(true);
		}

		public void menu(MLL list) {
			JFrame f = new JFrame("Pencere");
			JButton btn,btn1,btn2,btn3;
			JLabel l1 = new JLabel("Welcome To Stox! Please pick one of these below!");
			btn = new JButton("1.Enter Data");
			btn1 = new JButton("2.Edit Data");
			btn2 = new JButton("3.My Datas");
			btn3 = new JButton("4.exit");
			
			btn.setBounds(40, 50, 200, 25);
			btn1.setBounds(40, 80, 200, 25);
			btn2.setBounds(40, 110, 200, 25);
			btn3.setBounds(40, 140, 200, 25);
			
			btn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					enterData(list);
					f.setVisible(false);
					f.dispose();
					
				}
			});
			btn1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					 function(list);
					 f.setVisible(false);
					 f.dispose();
					
				}
			});
			btn2.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					 //Show Saved Data
					
				}
			});
			btn3.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					 System.exit(1);
					
				}
			});
			l1.setBounds(10, 20, 300, 25);
			f.add(l1);
			f.add(btn);
			f.add(btn1);
			f.add(btn2);
			f.add(btn3);
			f.setSize(400,400);
			f.setLayout(null);
			f.setVisible(true);
			
		}
		public void enterData(MLL list) {
			//BOþ bilgi girerse hata önleme eklenmeli
			//scroll eklenmeli
			//kategoriye göre arama eklenmeli
			//Dosyaya ürünlerin kaydý ve okunmasý eklenmeli.
			//BÝRDEN FAZLA DATA GÝRMEK ÝSTERSE BÝLGÝ EKLEME BUTONU KKÜÇÜK EKLE.
			ColumnNode temp = list.getHead();
			ElementNode tempE;
			JFrame f = new JFrame("Pencere");
			JTextField txf = new JTextField();
			JTextField txfN = new JTextField();
			JTextField txfB = new JTextField();
			JTextField txfS = new JTextField();
			
			JLabel l1 = new JLabel("Category");
			JLabel l2 = new JLabel("Name");
			JLabel l3 = new JLabel("Barcode");
			JLabel l4 = new JLabel("Stock");
			JLabel l5 = new JLabel("Waiting for enterance!");
			
			l1.setBounds(14, 5, 70, 30);
			l2.setBounds(95,5,70,30);
			l3.setBounds(162,5,70,30);
			l4.setBounds(240,5,70,30);
			l5.setBounds(150,100,70,30);
			
			txfN.setBounds(80,30,70,25);
			txfB.setBounds(150,30,70,25);
			txfS.setBounds(220,30,70,25);
			txf.setBounds(10,30,70,25);
			
			JButton btn = new JButton("EDIT!");
			JButton btn1 = new JButton("Menu");
			
			btn1.setBounds(450, 170, 100, 30);
			btn.setBounds(450, 100, 100, 30);
			
			btn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					Product product = new Product();
					product.setCategory(txf.getText());
					product.setName(txfN.getText());
					product.setBarcode(Integer.parseInt(txfB.getText()));//Add if user will enter integer value in this part.
					product.setStock(Integer.parseInt(txfS.getText()));
					list.addElement(product.getCategory(), product);
					l5.setText("Succesfull");
					//If same product entered, it would be canceled!
					
				}
			});
			btn1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					menu(list);
					f.setVisible(false);
					f.dispose();
					
				}
			});
			
			f.add(btn);
			f.add(btn1);
			f.add(txf);
			f.add(txfN);
			f.add(txfB);
			f.add(txfS);
			f.add(l1);
			f.add(l2);
			f.add(l3);
			f.add(l4);
			f.add(l5);
			f.setSize(400,400);
			f.setLayout(null);
			f.setVisible(true);
			
		}
		
		
		
		
	}