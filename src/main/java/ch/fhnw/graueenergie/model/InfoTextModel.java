package ch.fhnw.graueenergie.model;

import ch.fhnw.graueenergie.entity.EnergyConsumer;
import ch.fhnw.graueenergie.entity.EnergyEntity;
import ch.fhnw.graueenergie.entity.EnergyProducer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class InfoTextModel {

  private final Map<EnergyProducer, String> map;
  private String lastText = "";

  public InfoTextModel() {
    this.map = new HashMap<>();

    this.map.put(EnergyProducer.NO_ENERGY_PRODUCER, "");
    this.map.put(EnergyProducer.NUCLEAR_POWER_PLANT,
        "Ein Atomkraftwerk ist eine Art technischer Einrichtung, die die Energie aus der Spaltung von Atomkernen nutzt, um Strom zu erzeugen. Diese Art von Energiequelle ist sehr leistungsfähig und kann grosse Mengen an Strom erzeugen. Sie sind auch sehr zuverlässig und können für längere Zeit betrieben werden. Im Gegensatz zu erneuerbaren Energiequellen wie Solar- oder Windenergie können Atomkraftwerke kontinuierlich Strom produzieren, unabhängig von den Wetterbedingungen. Jedoch überschattet ein Nachteil die hohe Menge an Strom, welche gewonnen werden kann: die potenzielle Gefahr von nuklearen Unfällen. Wenn ein Atomkraftwerk beschädigt wird oder es zu einem Unfall kommt, kann es zu einer Freisetzung von Radioaktivität kommen. Dies kann schwerwiegende Auswirkungen auf die Umwelt und die Gesundheit von Menschen und Tieren haben. Zudem ist der Wert der Umweltbelastung bei Atomkraftwerken im Vergleich zu Wasser- oder Windkraftwerken sehr hoch (fast 10 Mal so hoch). Es ist auch wichtig zu beachten, dass die Entsorgung von nuklearem Abfall ein weiteres Problem bei der Nutzung von Atomkraftwerken darstellt. Radioaktiver Abfall bleibt für lange Zeit gefährlich und muss auf sichere Weise gelagert und entsorgt werden. Insgesamt kann ein Atomkraftwerk eine effektive Möglichkeit sein, um große Mengen an Strom zu erzeugen. Es gibt jedoch auch ernsthafte Bedenken hinsichtlich der Sicherheit und der Umweltauswirkungen. Es ist wichtig, diese Faktoren bei der Entscheidung zu berücksichtigen, welche Art von Energiequelle genutzt werden soll.");
    this.map.put(EnergyProducer.COAL_FIRED_POWER_STATION,
        "Ein Kohlekraftwerk verwendet Kohle als Brennstoff, um Strom zu erzeugen. Ein Kohlekraftwerk funktioniert, indem es Kohle in einem Ofen verbrennt, um Wärme zu erzeugen. Diese Wärme wird dann genutzt, um Wasser zu erhitzen und Dampf zu erzeugen. Der Dampf treibt dann eine Turbine an, die mit einem Generator verbunden ist, um Strom zu erzeugen. Diese Art von Energiequelle wurde in der Vergangenheit oft genutzt, da Kohle eine erschwingliche und leicht zugängliche Ressource ist. Kohlekraftwerke können mit Abstand die grösste Menge an Strom erzeugen und sind auch relativ günstig im Vergleich zu anderen Energiequellen. Sie können jedoch auch schwerwiegende Auswirkungen auf die Umwelt und die Gesundheit von Menschen und Tieren haben. Eine der grössten Herausforderungen bei der Nutzung von Kohlekraftwerken ist die Menge an Kohlendioxid, die bei der Verbrennung von Kohle freigesetzt wird. Kohlendioxid ist ein Treibhausgas, das zur globalen Erwärmung beitragen kann. Kohlekraftwerke sind eine der grössten Quellen von Kohlendioxidemissionen und tragen daher zur Klimaveränderung bei. Es gibt auch Probleme bei der Entsorgung von Asche, die bei der Verbrennung von Kohle entsteht. Aus diesen Gründen besitzt das Kohlekraftwerk leider auch die grösste Anzahl an Umweltbelastungspunkten. Insgesamt gibt es ernsthafte Bedenken hinsichtlich der Nutzung von Kohlekraftwerken.");
    this.map.put(EnergyProducer.HYDROELECTRIC_POWER_STATION,
        "Ein Wasserkraftwerk, meist ein Staudamm mit Turbinen, nutzt die Kraft des Wassers, um Energie zu erzeugen. Es ist eine erneuerbare Energiequelle, die keine fossilen Brennstoffe verwendet und keine schädlichen Emissionen (also beispielsweise hohe CO₂-Erzeugung) verursacht. Ein Wasserkraftwerk funktioniert, indem es Wasser aus einem Fluss oder einer Quelle in ein Reservoir leitet. Von dort wird das Wasser durch eine Turbine geleitet, die an einen Generator angeschlossen ist. Wenn das Wasser die Turbine passiert, dreht sie sich und erzeugt Strom im Generator. Im Gegensatz zu Solar- oder Windenergie ist Wasserkraftenergie eine konstante Energiequelle. Das bedeutet, dass es immer Energie produzieren kann, solange es genug Wasser gibt. Im Gegensatz dazu hängt die Energieproduktion von Solar- und Windenergie von Wetterbedingungen ab. Wasserkraftwerke können sehr effizient sein, da sie grosse Mengen an Strom erzeugen können. Zudem ist ein weiterer enormer Vorteil die extrem geringe Umweltbelastung. Grundsätzlich entstehen die wenigen Umweltbelastungspunkte lediglich beim \"Transport\" der Energie und bei der Installation und Einrichtung eines Wasserkraftwerks. Dies aufgrund der grossen Mengen an Beton und Stahl, welche gebraucht werden, um den Damm und die Turbinen zu bauen. Allerdings gibt es auch einzelne Nachteile bei der Nutzung von Wasserkraft. Einer davon ist, dass Wasserkraftwerke, wie der Name schon sagt, an einer Wasserquelle gebaut werden müssen, was die Energieversorgung von Städten ohne Flüsse oder Seen schwieriger macht. Zudem wird auch der Lebensraum von Tieren an und in Gewässern beeinträchtigt. ");
    this.map.put(EnergyProducer.WIND_POWER_PLANT,
        "Ein Windpark in unserer Simulation besteht aus 80 Windkraftwerken. Ein solches Windkraftwerk besteht in der Regel aus grossen Turbinen, die auf hohen Türmen montiert sind. Diese Turbinen werden von Wind angetrieben, der die Rotoren der Turbinen dreht, um elektrische Energie zu erzeugen. Ein solcher Windpark ist eine erneuerbare Energiequelle, da der Wind, der sie antreibt, ständig vorhanden ist. Im Gegensatz zu fossilen Brennstoffen wie Kohle oder Erdöl, die begrenzt sind und sich nicht regenerieren, ist Wind eine unerschöpfliche Energiequelle. Somit ist die gewonnene Energie erneuerbar, was bedeutet, dass sie aus einer unerschöpflichen Quelle stammt. Dies bedeutet auch, dass die Umwelt durch Windkraftwerke fast nicht belastet wird, was ein sehr positiver Aspekt der Windkraftwerke ist. Es ist jedoch auch wichtig zu beachten, dass Windkraftwerke auch negative Auswirkungen haben können. Zum Beispiel können sie Auswirkungen auf die Umwelt und den Lebensraum von Tieren haben, insbesondere wenn sie in der Nähe von Brutgebieten oder Zugrouten errichtet werden. Es ist wichtig, sorgfältig zu planen und Standorte zu wählen, die die Auswirkungen auf die Umwelt minimieren. Zudem erzeugen Windkraftwerke nicht besonders viel Strom im Vergleich zu Kohle- oder Wasserkraftwerken, was als weiterer kleiner Nachteil angesehen werden kann.");
    this.map.put(EnergyProducer.SOLAR_PANELS,
        "Eine Solaranlage, wie beispielsweise ein Solarpark, ist eine Vorrichtung, die die Energie der Sonne in Strom umwandelt. Sie besteht aus Solarzellen. Solche Solaranlagen sind eine erneuerbare Energiequelle, da die Sonne jeden Tag aufgeht und uns mit Energie versorgt. Im Gegensatz zu fossilen Brennstoffen wie Kohle oder Erdöl, die endlich sind, wird die Sonne noch sehr lange scheinen und uns mit Energie versorgen können. Solaranlagen generieren auf nicht allzu langer Sicht jedoch sehr wenig Energie. Damit sich eine solche Solaranlage wirklich lohnt, müssen mehrere solche Solarparks gebaut werden. Für die Herstellung von Solaranlagen werden jedoch Materialien und Ressourcen gebraucht, welche nicht besonders umweltfreundlich sind. Daher ist, obwohl der Energiegewinn ziemlich gering ist, auch die Belastung der Umwelt ein weiterer Kritikpunkt der Solaranlagen. Für den privaten Gebrauch können aufgrund der erneuerbaren Energiequelle Solaranlagen positiv eingesetzt werden. Hingegen um eine komplette Stadt zu versorgen, steigt die Belastung der Umwelt stark. \n");
  }

  public String getInfoText(EnergyEntity[] lastChange) {
    List<EnergyEntity> consumerList = Arrays.stream(lastChange)
        .filter(energyEntity -> energyEntity.getConsumer() != null).toList();
    EnergyConsumer energyConsumer =
        consumerList.size() == 1 ? consumerList.get(0).getConsumer() : null;

    if (energyConsumer != null && energyConsumer.id == EnergyConsumer.NO_CONSUMER.id) {
      String welcomeText = "Willkommen, bitte wählen Sie einen Verbaucher aus (Stadt, Dorf oder Industriegebiet).";
      return saveLastTextAndReturn(welcomeText);
    }

    if (lastChange.length != 0) {
      return saveLastTextAndReturn(map.get(lastChange[lastChange.length - 1].getProducer()));
    }

    return lastText;
  }

  private String saveLastTextAndReturn(String newText) {
    lastText = newText;
    return newText;
  }
}
