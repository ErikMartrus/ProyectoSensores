package pem.proyectoSensores.series.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SetOfSeries {

	private static SetOfSeries singleton = null;
	private ArrayList<ItemSeries> listSeries;
	
	private SetOfSeries() {
		listSeries = new ArrayList<ItemSeries>();
		listSeries.add(new ItemSeries("bigBang","The Big Bang Theory","The Big Bang Theory is an American television sitcom created by Chuck Lorre and Bill Prady, both of whom serve as executive producers on the series, along with Steven Molaro. All three also serve as head writers. The show premiered on CBS on September 24, 2007." +
				" The twelfth and final season which will run through 2018–19 premiered on September 24, 2018, consisting of 24 episodes", "https://upload.wikimedia.org/wikipedia/en/7/7b/The_Big_Bang_Theory_%28Official_Title_Card%29.png",
				"http://commonsware.com/misc/test2.3gp"));
		listSeries.add(new ItemSeries("narcos","Narcos","Narcos is an American crime drama web television series created and produced by Chris Brancato, Carlo Bernard, and Doug Miro.\n" +
				"\n" +
				"Set and filmed in Colombia, seasons one and two are based on the story of drug kingpin Pablo Escobar, who became a billionaire through the production and distribution of cocaine. The series also focuses on Escobar's interactions with drug lords, " +
				"Drug Enforcement Administration (DEA) agents, and various opposition entities.[1][2] Season three picks up after the fall of Escobar and continues to follow the DEA as they try to shutdown the rise of the infamous Cali Cartel.", "https://en.wikipedia.org/wiki/Narcos#/media/File:Narcos_title_card.jpg", "http://commonsware.com/misc/test2.3gp"));
		listSeries.add(new ItemSeries("happy","Happy!","Happy! is an American fantasy black comedy crime television series based on the four-issue comic book series of the same name created by writer Grant Morrison and artist Darick Robertson, with Brian Taylor serving as director for five of the eight episodes.",
				"https://en.wikipedia.org/wiki/Happy!_(TV_series)#/media/File:Happy!_Title_Card.png", "http://commonsware.com/misc/test2.3gp"));
		
	}
	public static SetOfSeries getInstance() {
		if (singleton == null)
			singleton = new SetOfSeries();
		return singleton;
	}

	public ArrayList<ItemSeries> getListSeries() {
		return listSeries;
	}

	public void setListSeries(ArrayList<ItemSeries> listSeries) {
		this.listSeries = listSeries;
	}
	
	public void agregarItem(ItemSeries nuevo) {
		listSeries.add(nuevo);
		Collections.sort(listSeries, new Comparator<ItemSeries>() {
	        @Override
	        public int compare(ItemSeries item1, ItemSeries item2) {
	            return  item1.getName().compareTo(item2.getName());
	        }
	    });
	}
}


