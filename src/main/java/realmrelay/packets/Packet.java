package realmrelay.packets;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import realmrelay.GETXmlParse;
import realmrelay.ROTMGRelay;
import realmrelay.data.IData;
import realmrelay.packets.client.*;
import realmrelay.packets.server.*;


public abstract class Packet implements IData {

//        public int futureTime = 0;//Time to send packet in future
        private static boolean initialized = false;
	public static final Map<Integer,Class<? extends Packet>> packetIdtoClassMap = new HashMap<>();
	
	public static void init() 
        {
            if(!initialized)
            {
                initialized = true;
                for (int i = 0; i < 127; i++)
                {
                    packetIdtoClassMap.put(i, null);
                }
                List<Class<? extends Packet>> list = new LinkedList<>();

                list.add(AcceptTradePacket.class);
                list.add(AccountListPacket.class);
                list.add(AllyShootPacket.class);
                list.add(AOEAckPacket.class);
                list.add(AOEPacket.class);
                list.add(BuyPacket.class);
                list.add(BuyResultPacket.class);
                list.add(CancelTradePacket.class);
                list.add(ChangeGuildRankPacket.class);
                list.add(ChangeTradePacket.class);
                list.add(CheckCreditsPacket.class);
                list.add(ChooseNamePacket.class);
                list.add(ClientStatPacket.class);
                list.add(CreateSuccessPacket.class);
                list.add(CreateGuildPacket.class);
                list.add(CreateGuildResultPacket.class);
                list.add(CreatePacket.class);
                list.add(DamagePacket.class);
                list.add(DeathPacket.class);
                list.add(EditAccountListPacket.class);
                list.add(EnemyHitPacket.class);
                list.add(EscapePacket.class);
                list.add(FailurePacket.class);
                list.add(FilePacket.class);
                list.add(GlobalNotificationPacket.class);
                list.add(GoToAckPacket.class);
                list.add(GoToPacket.class);
                list.add(GroundDamagePacket.class);
                list.add(GuildInvitePacket.class);
                list.add(GuildRemovePacket.class);
                list.add(HelloPacket.class);
                list.add(InvDropPacket.class);
                list.add(InvitedToGuildPacket.class);
                list.add(InvResultPacket.class);
                list.add(InvSwapPacket.class);
                list.add(JoinGuildPacket.class);
                list.add(LoadPacket.class);
                list.add(MapInfoPacket.class);
                list.add(MovePacket.class);
                list.add(NameResultPacket.class);
                list.add(NewTickPacket.class);
                list.add(NotificationPacket.class);
                list.add(OtherHitPacket.class);
                list.add(PicPacket.class);
                list.add(PingPacket.class);
                list.add(PlayerHitPacket.class);
                list.add(PlayerShootPacket.class);
                list.add(PlayerTextPacket.class);
                list.add(PlaySoundPacket.class);
                list.add(PongPacket.class);
                list.add(QuestObjIdPacket.class);
                list.add(ReconnectPacket.class); //Thanks to OkYk for the temporary solution.
                list.add(RequestTradePacket.class);
                list.add(ReskinPacket.class);
                list.add(SetConditionPacket.class);
                list.add(ServerPlayerShootPacket.class);
                list.add(ShootAckPacket.class);
                list.add(ShootPacket.class);
                list.add(ShowEffectPacket.class);
                list.add(SquareHitPacket.class);
                list.add(TeleportPacket.class);
                list.add(TextPacket.class);
                list.add(TradeAcceptedPacket.class);
                list.add(TradeChangedPacket.class);
                list.add(TradeDonePacket.class);
                list.add(TradeRequestedPacket.class);
                list.add(TradeStartPacket.class);
                list.add(UpdateAckPacket.class);
                list.add(UpdatePacket.class);
                list.add(UseItemPacket.class);
                list.add(UsePortalPacket.class);
                try
                {
                    ROTMGRelay.echo("Mapping " + GETXmlParse.packetMap.size() + " packets.");
                    for (Class<? extends Packet> packetClass : list)
                    {
                        Packet packet = packetClass.newInstance();

                        if (packet.id() == -1)
                        {
                            System.err.println("trying to map a shit packet");
//					packetIdtoClassMap.set(100, packetClass);
                        }
                        else
                        {
                            System.err.println("Mapping : " + packet.id() + " to: " + packetClass);
                            packetIdtoClassMap.put(packet.id(), packetClass);
                        }
                    }
//			for (Entry<String, Integer> entry: GETXmlParse.packetMap.entrySet()) {
//				byte id = entry.getValue().byteValue();
//				Packet packet = Packet.create(id);
//			/*	if (packet instanceof UnknownPacket) {
//					ROTMGRelay.echo("Warning : Not mapped packet : " + entry.getKey() + " -> " + id);
//				}*/
//			}
                }
                catch (IllegalAccessException | InstantiationException e)
                {
                    e.printStackTrace();
                }
            }
		
	}
	
	/**
	 * Creates new packet from packet id
	 * @param id
	 * @return
	 * @throws Exception 
	 * @throws InstantiationException 
	 */
	public static Packet create(int id) throws Exception {
//            System.err.println("Making a packet");
//            
		Class<? extends Packet> packetClass = packetIdtoClassMap.get(id);
//		if (packetClass == null) {
//			UnknownPacket packet = new UnknownPacket();
//			packet.setId(id);
//			return packet;
//		}
                    if(packetClass != null)
                    {
//                        System.err.println("creating packet with id: " + id + " packet class name: " + packetClass.getCanonicalName());
                                    return packetClass.newInstance();
                    }
                    return null;
	}
	
	/**
	 * Creates new packet from packet id and packet bytes
	 * @param id
	 * @param bytes
	 * @return
	 * @throws IOException
	 */
	public static Packet create(byte id, byte[] bytes) throws Exception {
		Packet packet = Packet.create(id);
                if(packet == null)
                {
//                    System.err.println("Created null packet");
                    throw new Exception("Made a null packet from id: " + id);
                }
		packet.parseFromInput(new DataInputStream(new ByteArrayInputStream(bytes)));
		int byteLength = packet.getBytes().length;
		if (byteLength != bytes.length) {
//			ROTMGRelay.echo(packet + " byte length is " + byteLength + " after parsing, but was " + bytes.length + " before parsing. Try updating your packets.xml");
		}
		return packet;
	}
	
	public byte[] getBytes() throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(baos);
		this.writeToOutput(out);
		return baos.toByteArray();
	}
	
	public String getName() {
		String simpleName = this.getClass().getSimpleName();
		simpleName = simpleName.substring(0, simpleName.indexOf("Packet"));
		return simpleName.toLowerCase();
	}
	
	public int id() {
		String name = this.getName();
		Integer id = GETXmlParse.packetMap.get(name);
		if (id == null) {
			return -1;
		}
		return id.byteValue();
	}
	
//  public String toString()
//    {
//        StringBuilder result = new StringBuilder();
//        String newLine = System.getProperty("line.separator");
//
//        result.append(this.getClass().getName());
//        result.append(" Object {");
//        result.append(newLine);
//
//        //determine fields declared in this class only (no fields of superclass)
//        Field[] fields = this.getClass().getDeclaredFields();
//
//        //print field names paired with their values
//        for (Field field : fields)
//        {
//            result.append("  ");
//            try
//            {
//                result.append(field.getName());
//                result.append(": ");
//                //requires access to private field:
//                result.append(field.get(this));
//            }
//            catch (IllegalAccessException ex)
//            {
//                System.out.println(ex);
//            }
//            result.append(newLine);
//        }
//        result.append("}");
//
//        return result.toString();
//    }

}
