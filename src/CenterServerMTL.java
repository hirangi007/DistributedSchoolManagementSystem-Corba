import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.helper.LogHelper;
import com.users.Student;
import com.users.Teacher;

import CorbaApp.Center;
import CorbaApp.CenterHelper;
import CorbaApp.CenterPOA;

class CenterServerMTLImplementation extends CenterPOA implements Serializable {
	private ORB orb;

	public HashMap<String, ArrayList<Object>> srtrRecords;
	public ArrayList<Object> srtrMTL, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z;
	JsonParser parser;
	String lastSRecordId = new String();
	String lastTRecordId = new String();
	LogHelper helper;
	Logger logger = Logger.getLogger(CenterServerMTLImplementation.class);

	public void setORB(ORB orb_val) {
		orb = orb_val;
	}

	public CenterServerMTLImplementation() {
		super();

		srtrRecords = new HashMap<String, ArrayList<Object>>();
		srtrMTL = new ArrayList<Object>();
		a = new ArrayList<Object>();
		b = new ArrayList<Object>();
		c = new ArrayList<Object>();
		d = new ArrayList<Object>();
		e = new ArrayList<Object>();
		f = new ArrayList<Object>();
		g = new ArrayList<Object>();
		h = new ArrayList<Object>();
		i = new ArrayList<Object>();
		j = new ArrayList<Object>();
		k = new ArrayList<Object>();
		l = new ArrayList<Object>();
		m = new ArrayList<Object>();
		n = new ArrayList<Object>();
		o = new ArrayList<Object>();
		p = new ArrayList<Object>();
		q = new ArrayList<Object>();
		r = new ArrayList<Object>();
		s = new ArrayList<Object>();
		t = new ArrayList<Object>();
		u = new ArrayList<Object>();
		v = new ArrayList<Object>();
		w = new ArrayList<Object>();
		x = new ArrayList<Object>();
		y = new ArrayList<Object>();
		z = new ArrayList<Object>();

		helper = new LogHelper();
		helper.setupLogFile("log/MTLServer.log");
	}

	// implement shutdown() method
	public void shutdown() {
		orb.shutdown(false);
	}

	/**
	 * Method to add default records to the hashmap
	 */
	public void addDefaultRecords() {
		File student = new File("res/student.json");
		File teacher = new File("res/teacher.json");
		Reader reader;
		try {

			reader = new BufferedReader(new FileReader(student.getAbsolutePath()));
			JsonParser parser = new JsonParser();
			JsonArray array = parser.parse(reader).getAsJsonArray();

			if (array != null) {
				for (int i = 0; i < 4; i++) {
					JsonObject object = (JsonObject) array.get(i);
					Student s = new Student(object.get("fname").getAsString(), object.get("lname").getAsString(),
							object.get("coursesRegistered").getAsString(), object.get("status").getAsString(),
							object.get("statusDueDate").getAsString(), object.get("id").getAsString());
					srtrMTL.add(s);
					lastSRecordId = object.get("id").getAsString();
				}
			}

			reader = new BufferedReader(new FileReader(teacher.getAbsolutePath()));
			array = parser.parse(reader).getAsJsonArray();

			if (array != null) {
				for (int i = 0; i < 4; i++) {
					JsonObject object = (JsonObject) array.get(i);
					Teacher t = new Teacher(object.get("fname").getAsString(), object.get("lname").getAsString(),
							object.get("address").getAsString(), object.get("phone").getAsString(),
							object.get("specialization").getAsString(), object.get("location").getAsString(),
							object.get("id").getAsString());
					srtrMTL.add(t);
					lastTRecordId = object.get("id").getAsString();
				}
			}

			for (int ij = 0; ij < srtrMTL.size(); ij++) {
				addToMap(srtrMTL.get(ij));
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to add teacher/student object to the hashmap
	 * 
	 * @param obj
	 */
	public void addToMap(Object obj) {

		Student stud;
		Teacher teach;
		Character ch;

		if (obj instanceof Student) {
			stud = (Student) obj;
			ch = stud.getLname().toUpperCase().charAt(0);
		} else {
			teach = (Teacher) obj;
			ch = teach.getLname().toUpperCase().charAt(0);
		}
		switch (ch) {
		case 'A':
			a.add(obj);
			break;
		case 'B':
			b.add(obj);
			break;
		case 'C':
			c.add(obj);
			break;
		case 'D':
			d.add(obj);
			break;
		case 'E':
			e.add(obj);
			break;
		case 'F':
			f.add(obj);
			break;
		case 'G':
			g.add(obj);
			break;
		case 'H':
			h.add(obj);
			break;
		case 'I':
			i.add(obj);
			break;
		case 'J':
			j.add(obj);
			break;
		case 'K':
			k.add(obj);
			break;
		case 'L':
			l.add(obj);
			break;
		case 'M':
			m.add(obj);
			break;
		case 'N':
			n.add(obj);
			break;
		case 'O':
			o.add(obj);
			break;
		case 'P':
			p.add(obj);
			break;
		case 'Q':
			q.add(obj);
			break;
		case 'R':
			r.add(obj);
			break;
		case 'S':
			s.add(obj);
			break;
		case 'T':
			t.add(obj);
			break;
		case 'U':
			u.add(obj);
			break;
		case 'V':
			v.add(obj);
			break;
		case 'W':
			w.add(obj);
			break;
		case 'X':
			x.add(obj);
			break;
		case 'Y':
			y.add(obj);
			break;
		case 'Z':
			z.add(obj);
			break;
		default:
			break;
		}
		srtrRecords.put("A", a);
		srtrRecords.put("B", b);
		srtrRecords.put("C", c);
		srtrRecords.put("D", d);
		srtrRecords.put("E", e);
		srtrRecords.put("F", f);
		srtrRecords.put("G", g);
		srtrRecords.put("H", h);
		srtrRecords.put("I", i);
		srtrRecords.put("J", j);
		srtrRecords.put("K", k);
		srtrRecords.put("L", l);
		srtrRecords.put("M", m);
		srtrRecords.put("N", n);
		srtrRecords.put("O", o);
		srtrRecords.put("P", p);
		srtrRecords.put("Q", q);
		srtrRecords.put("R", r);
		srtrRecords.put("S", s);
		srtrRecords.put("T", t);
		srtrRecords.put("U", u);
		srtrRecords.put("V", v);
		srtrRecords.put("W", w);
		srtrRecords.put("X", x);
		srtrRecords.put("Y", y);
		srtrRecords.put("Z", z);
	}

	@Override
	public String createTRecord(String managerID, String fname, String lastName, String address, String phone,
			String specialization, String location) {
		int id = Integer.parseInt(lastTRecordId.substring(3, 8));
		lastTRecordId = "MTR" + "" + ++id;
		Teacher t = new Teacher(fname, lastName, address, phone, specialization, location, lastTRecordId);
		logger.info(managerID + "| createTRecord method | Teacher information - [{" + fname + ", " + lastName + ", "
				+ address + ", " + phone + ", " + specialization + ", " + location + "}]");
		addToMap(t);
		logger.info(managerID + "| Teacher created successfully with id " + lastTRecordId);
		return "hi";
	}

	@Override
	public String createSRecord(String managerID, String fname, String lastName, String courseRegistered, String status,
			String statusDate) {
		int id = Integer.parseInt(lastSRecordId.substring(3, 8));
		lastSRecordId = "MSR" + "" + ++id;
		Student s = new Student(fname, lastName, courseRegistered, status, statusDate, lastSRecordId);
		logger.info(managerID + "| createSRecord method | Student information - [{" + fname + ", " + lastName + ", "
				+ courseRegistered + ", " + status + ", " + statusDate + "}]");
		addToMap(s);
		logger.info(managerID + "| Student created successfully with id " + lastSRecordId);
		return "hi";
	}

	@Override
	public String getRecordCounts(String managerID) {
		logger.info(managerID + "| Using getRecordCounts method.");
		DatagramSocket socket = null;
		String responseMsg = new String();
		try {
			logger.info(managerID + "| Creating UDP connection with LVL and DDO server to get record counts.");
			socket = new DatagramSocket();
			byte[] message = "Record Count".getBytes();
			InetAddress host = InetAddress.getByName("localhost");
			DatagramPacket request = new DatagramPacket(message, message.length, host, 1212);
			socket.send(request);
			logger.info(managerID + "| Sent request to LVL server - localhost:1212");
			byte[] buffer = new byte[10];
			DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
			socket.receive(reply);
			logger.info(managerID + "| Reply from LVL server : " + new String(reply.getData()));
			responseMsg = new String(reply.getData());
			socket.close();
			logger.info(managerID + "| Connection closed with LVL server.");
			socket = new DatagramSocket();
			request = new DatagramPacket(message, message.length, host, 1111);
			socket.send(request);
			logger.info(managerID + "| Sent request to DDO server - localhost:1111");
			buffer = new byte[10];
			reply = new DatagramPacket(buffer, buffer.length);
			socket.receive(reply);
			logger.info(managerID + "| Reply from DDO server - " + new String(reply.getData()));
			responseMsg = responseMsg + ", " + new String(reply.getData()) + ", MTL " + getCount();
			socket.close();
			logger.info(managerID + "| Connection closed with DDO server.");
		} catch (SocketException e) {
			logger.error(managerID + "| Error in socket connection | " + e.toString());
			e.printStackTrace();
		} catch (UnknownHostException e) {
			logger.error(managerID + "| Unknownhost exception | " + e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(managerID + "| IO exception | " + e.toString());
			e.printStackTrace();
		}

		return responseMsg;
	}

	public int getCount() {
		int counter = 0;
		if (srtrRecords.size() > 0) {
			for (int i = 65; i < 91; i++) {
				String key = Character.toString((char) i);
				ArrayList<Object> array = srtrRecords.get(key);
				counter += array.size();
			}
			return counter;
		} else {
			return 0;
		}

	}

	@Override
	public String editRecord(String managerID, String recordID, String fieldName, String newValue) {
		Boolean result = false;
		String result_string;
		logger.info(managerID + "| Using editRecord method. Record ID : " + recordID);
		if (recordID.substring(0, 3).equals("MSR")) {
			Student s;
			for (int i = 65; i < 91; i++) {
				String key = Character.toString((char) i);
				ArrayList<Object> array = srtrRecords.get(key);
				for (int j = 0; j < array.size(); j++) {
					if (array.get(j) instanceof Student) {
						s = (Student) array.get(j);
						if (s.getId().equals(recordID)) {
							System.out.println("Student found");
							logger.info(managerID + "| Record id " + recordID + " identified as a student.");
							result = true;
							if (fieldName.equals("status")) {
								if (newValue.equals("active") || newValue.equals("deactive")) {
									s.setStatus(newValue);
									logger.info(
											managerID + "| Record - " + recordID + " status changed to " + newValue);
									result = true;
								} else {
									logger.info(managerID + "| Entered invalid status number.");
									result = false;
								}
							} else if (fieldName.equals("statusDueDate")) {
								Pattern pattern;
								Matcher matcher;
								String DATE_PATTERN = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)";
								pattern = Pattern.compile(DATE_PATTERN);
								matcher = pattern.matcher(newValue);
								if (matcher.matches()) {
									s.setStatusDueDate(newValue);
									logger.info(managerID + "| Record - " + recordID + " status date changed to "
											+ newValue);
									result = true;
								} else {
									logger.info(managerID + "| Entered invalid date.");
									result = false;
								}
							} else if (fieldName.equals("coursesRegistered")) {
								s.setCoursesRegistered(newValue);
								logger.info(managerID + "| Record - " + recordID + " registered courses changed to "
										+ newValue);
								result = true;
							}
							if (result)
								result_string = "hi";
							else
								result_string = "bye";
							return result_string;
						} else {
							result = false;
						}
					}
				}
			}
		} else if (recordID.substring(0, 3).equals("MTR")) {
			Teacher t;
			for (int i = 65; i < 91; i++) {
				String key = Character.toString((char) i);
				ArrayList<Object> array = srtrRecords.get(key);
				for (int j = 0; j < array.size(); j++) {
					if (array.get(j) instanceof Teacher) {
						t = (Teacher) array.get(j);
						if (t.getId().equals(recordID)) {
							logger.info(managerID + "| Record id " + recordID + " identified as a teacher.");
							result = true;
							if (fieldName.equals("address")) {
								t.setAddress(newValue);
								logger.info(managerID + "| Record - " + recordID + " address changed to " + newValue);
								result = true;
							} else if (fieldName.equals("location")) {
								t.setLocation(newValue);
								logger.info(managerID + "| Record - " + recordID + " location changed to " + newValue);
								result = true;
							} else if (fieldName.equals("phone")) {
								t.setPhone(newValue);
								logger.info(
										managerID + "| Record - " + recordID + " phone number changed to " + newValue);
								result = true;

							}
							if (result)
								result_string = "hi";
							else
								result_string = "bye";
							return result_string;
						} else {
							System.out.println("hiii");
							result = false;
						}
					}

				}
			}

		} else {
			result = false;
		}
		if (!result) {
			logger.info(managerID + "| Record - " + recordID + " not found.");
			System.out.println("no record found");
			if (result)
				result_string = "hi";
			else
				result_string = "bye";
			return result_string;
		} else {
			if (result)
				result_string = "hi";
			else
				result_string = "bye";
			return result_string;
		}
	}

	@Override
	public String transferRecord(String managerID, String recordID, String remoteCenterServerName) {

		if (recordID.substring(0, 3).equals("MSR")) {
			Student s;
			for (int i = 65; i < 91; i++) {
				String key = Character.toString((char) i);
				ArrayList<Object> array = srtrRecords.get(key);
				System.out.println(array.size());
				for (int j = 0; j < array.size(); j++) {
					if (array.get(j) instanceof Student) {
						s = (Student) array.get(j);
						if (s.getId().equals(recordID)) {
							if (remoteCenterServerName.equals("LVL")) {
								logger.info(managerID + "| Using transferRecord method.");
								DatagramSocket socket = null;
								String responseMsg = new String();
								try {
									logger.info(managerID
											+ "| Creating UDP connection with LVL server to transfer record.");
									socket = new DatagramSocket();
									ByteArrayOutputStream out = new ByteArrayOutputStream();
									ObjectOutputStream os = new ObjectOutputStream(out);
									os.writeObject((Object) s);
									byte[] message = out.toByteArray();
									InetAddress host = InetAddress.getByName("localhost");
									DatagramPacket request = new DatagramPacket(message, message.length, host, 1213);
									socket.send(request);
									logger.info(managerID + "| Sent request to LVL server - localhost:1213");
									byte[] buffer = new byte[10];
									DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
									socket.receive(reply);
									logger.info(managerID + "| Reply from LVL server : " + new String(reply.getData()));
									responseMsg = new String(reply.getData());
									System.out.println(responseMsg);
									socket.close();
									logger.info(managerID + "| Connection closed with LVL server.");
								} catch (SocketException e) {
									logger.error(managerID + "| Error in socket connection | " + e.toString());
									e.printStackTrace();
								} catch (UnknownHostException e) {
									logger.error(managerID + "| Unknownhost exception | " + e.toString());
									e.printStackTrace();
								} catch (IOException e) {
									logger.error(managerID + "| IO exception | " + e.toString());
									e.printStackTrace();
								}
								array.remove(j);
								return responseMsg;
							} else if (remoteCenterServerName.equals("DDO")) {
								logger.info(managerID + "| Using transferRecord method.");
								DatagramSocket socket = null;
								String responseMsg = new String();
								try {
									logger.info(managerID
											+ "| Creating UDP connection with DDO server to transfer record.");
									socket = new DatagramSocket();
									ByteArrayOutputStream out = new ByteArrayOutputStream();
									ObjectOutputStream os = new ObjectOutputStream(out);
									os.writeObject(s);
									byte[] message = out.toByteArray();
									InetAddress host = InetAddress.getByName("localhost");
									DatagramPacket request = new DatagramPacket(message, message.length, host, 1112);
									socket.send(request);
									logger.info(managerID + "| Sent request to DDO server - localhost:1112");
									byte[] buffer = new byte[10];
									DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
									socket.receive(reply);
									logger.info(managerID + "| Reply from DDO server : " + new String(reply.getData()));
									responseMsg = new String(reply.getData());
									socket.close();
									logger.info(managerID + "| Connection closed with DDO server.");
								} catch (SocketException e) {
									logger.error(managerID + "| Error in socket connection | " + e.toString());
									e.printStackTrace();
								} catch (UnknownHostException e) {
									logger.error(managerID + "| Unknownhost exception | " + e.toString());
									e.printStackTrace();
								} catch (IOException e) {
									logger.error(managerID + "| IO exception | " + e.toString());
									e.printStackTrace();
								}
								array.remove(j);
								return responseMsg;
							}
						}
					}
				}
			}
		} else if (recordID.substring(0, 3).equals("MTR")) {
			Teacher t;
			for (int i = 65; i < 91; i++) {
				String key = Character.toString((char) i);
				ArrayList<Object> array = srtrRecords.get(key);
				for (int j = 0; j < array.size(); j++) {
					if (array.get(j) instanceof Teacher) {
						t = (Teacher) array.get(j);
						if (t.getId().equals(recordID)) {

							if (remoteCenterServerName.equals("LVL")) {
								logger.info(managerID + "| Using transferRecord method.");
								DatagramSocket socket = null;
								String responseMsg = new String();
								try {
									logger.info(managerID
											+ "| Creating UDP connection with LVL server to transfer record.");
									socket = new DatagramSocket();
									ByteArrayOutputStream out = new ByteArrayOutputStream();
									ObjectOutputStream os = new ObjectOutputStream(out);
									os.writeObject(t);
									byte[] message = out.toByteArray();
									InetAddress host = InetAddress.getByName("localhost");
									DatagramPacket request = new DatagramPacket(message, message.length, host, 1213);
									socket.send(request);
									logger.info(managerID + "| Sent request to LVL server - localhost:1213");
									byte[] buffer = new byte[10];
									DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
									socket.receive(reply);
									logger.info(managerID + "| Reply from LVL server : " + new String(reply.getData()));
									responseMsg = new String(reply.getData());
									socket.close();
									logger.info(managerID + "| Connection closed with LVL server.");
								} catch (SocketException e) {
									logger.error(managerID + "| Error in socket connection | " + e.toString());
									e.printStackTrace();
								} catch (UnknownHostException e) {
									logger.error(managerID + "| Unknownhost exception | " + e.toString());
									e.printStackTrace();
								} catch (IOException e) {
									logger.error(managerID + "| IO exception | " + e.toString());
									e.printStackTrace();
								}
								array.remove(j);
								return responseMsg;
							} else if (remoteCenterServerName.equals("DDO")) {
								logger.info(managerID + "| Using transferRecord method.");
								DatagramSocket socket = null;
								String responseMsg = new String();
								try {
									logger.info(managerID
											+ "| Creating UDP connection with DDO server to transfer record.");
									socket = new DatagramSocket();
									ByteArrayOutputStream out = new ByteArrayOutputStream();
									ObjectOutputStream os = new ObjectOutputStream(out);
									os.writeObject(t);
									byte[] message = out.toByteArray();
									InetAddress host = InetAddress.getByName("localhost");
									DatagramPacket request = new DatagramPacket(message, message.length, host, 1112);
									socket.send(request);
									logger.info(managerID + "| Sent request to DDO server - localhost:1112");
									byte[] buffer = new byte[10];
									DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
									socket.receive(reply);
									logger.info(managerID + "| Reply from DDO server : " + new String(reply.getData()));
									responseMsg = new String(reply.getData());
									socket.close();
									logger.info(managerID + "| Connection closed with DDO server.");
								} catch (SocketException e) {
									logger.error(managerID + "| Error in socket connection | " + e.toString());
									e.printStackTrace();
								} catch (UnknownHostException e) {
									logger.error(managerID + "| Unknownhost exception | " + e.toString());
									e.printStackTrace();
								} catch (IOException e) {
									logger.error(managerID + "| IO exception | " + e.toString());
									e.printStackTrace();
								}
								array.remove(j);
								return responseMsg;
							}
						}
					}
				}
			}
		}
		return "record not found";
	}

}

public class CenterServerMTL extends Thread {
	public static void main(String args[]) {

		CenterServerMTLImplementation centerServerMTLImplementation = new CenterServerMTLImplementation();
		try {
			centerServerMTLImplementation.addDefaultRecords();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					String args1 = "-ORBInitialPort 1050 -ORBInitialHost localhost";
					String arg[] = args1.split(" ");
					ORB orb = ORB.init(arg, null);
					POA rootpoa;
					rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
					rootpoa.the_POAManager().activate();
					centerServerMTLImplementation.setORB(orb);
					org.omg.CORBA.Object ref = rootpoa.servant_to_reference(centerServerMTLImplementation);
					Center href = CenterHelper.narrow(ref);
					org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
					NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
					String name = "MTLServer";
					NameComponent path[] = ncRef.to_name(name);
					ncRef.rebind(path, href);
					System.out.println("MTL Server ready and waiting ...");
					orb.run();
				} catch (Exception e) {

				}
			}
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				try {

					while (true) {
						DatagramSocket socket = new DatagramSocket(2964);
						byte[] buffer = new byte[1000];
						DatagramPacket request = new DatagramPacket(buffer, buffer.length);
						socket.receive(request);
						centerServerMTLImplementation.logger
								.info("Request received from : " + request.getAddress() + ":" + request.getPort());
						String replyStr = "MTL : " + centerServerMTLImplementation.getCount();
						byte[] buffer1 = replyStr.getBytes();
						DatagramPacket reply = new DatagramPacket(buffer1, buffer1.length, request.getAddress(),
								request.getPort());
						socket.send(reply);
						centerServerMTLImplementation.logger
								.info("Reply sent to : " + request.getAddress() + ":" + request.getPort());
						socket.close();

					}

				}

				catch (Exception e) {
					System.err.println("ERROR: " + e);
					e.printStackTrace(System.out);
				}

				System.out.println("HelloServer Exiting ...");

			}
		}).start();

		new Thread(new Runnable() {
			public void run() {
				try {
					DatagramSocket socket1 = new DatagramSocket(2965);
					byte[] buffer12 = new byte[1000];
					DatagramPacket request1 = new DatagramPacket(buffer12, buffer12.length);
					socket1.receive(request1);
					ByteArrayInputStream in = new ByteArrayInputStream(buffer12);
					ObjectInputStream is = new ObjectInputStream(in);
					Object o = is.readObject();
					String replyStr1 = null;
					if (o instanceof Student) {
						Student s = (Student) o;
						int id = Integer.parseInt(centerServerMTLImplementation.lastSRecordId.substring(3, 8));
						centerServerMTLImplementation.lastSRecordId = "MSR" + "" + ++id;
						s.setId(centerServerMTLImplementation.lastSRecordId);
						centerServerMTLImplementation.addToMap(s);
						replyStr1 = "Success";
					} else if (o instanceof Teacher) {
						Teacher t = (Teacher) o;
						int id = Integer.parseInt(centerServerMTLImplementation.lastTRecordId.substring(3, 8));
						centerServerMTLImplementation.lastTRecordId = "MTR" + "" + ++id;
						t.setId(centerServerMTLImplementation.lastTRecordId);
						centerServerMTLImplementation.addToMap(t);
						replyStr1 = "Success";
					}
					centerServerMTLImplementation.logger
							.info("Request received from : " + request1.getAddress() + ":" + request1.getPort());
					byte[] buffer11 = replyStr1.getBytes();
					DatagramPacket reply1 = new DatagramPacket(buffer11, buffer11.length, request1.getAddress(),
							request1.getPort());
					socket1.send(reply1);
					centerServerMTLImplementation.logger
							.info("Reply sent to : " + request1.getAddress() + ":" + request1.getPort());
					socket1.close();
				} catch (Exception e) {
					System.err.println("ERROR: " + e);
					e.printStackTrace(System.out);
				}
			}
		}).start();
	}

}