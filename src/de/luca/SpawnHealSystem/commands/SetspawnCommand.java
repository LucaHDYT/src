import de.luca.SpawnHealSystem.main.Main;
import de.luca.SpawnHealSystem.util.ConfigUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetspawnCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)){
            sender.sendMessage(Main.getPrefix()+ "§cDu musst ein Spieler sein.");
            return true;
        }

        final Player player = ((Player) sender);

        if (player.hasPermission("setspawn.setspawn")){
            switch (args.length){
                case 0:
                    ConfigUtils.config.set("Spawn",player.getLocation());
                    ConfigUtils.savedata();
                    player.sendMessage(Main.getPrefix() + "§aDer §6Spawn §awurde erfolgreich gesetzt.");
                    break;



                default:
                    player.sendMessage("§cBitte Benutze: /setspawn");
                    break;
            }




        }else {
            player.sendMessage(Main.getPrefix() + "§cDazu hast du keine Rechte!");
        }



        return true;
    }
}
