# website-crawler
Java application with GUI. Final Project for Java II (Data Structures) class.

Contributers: Yaotian He, Dau Lam

<h2>Language written in:</h2> 
<p>Java</p>

<h2>Compilation Platform:</h2>
<p>Windows / Mac OS X / Netbeans</p>

<h2>Description/usage:</h2>
<p>We list the requirements and functions to be (1) require a seeder at start, (2) be able to read the contents of a webpage, (3) can identify all the webpage links and add to a queue, (4) create a pool of data from the webpage, normalizing the words and stored into a Set, (5) have the data be in an inverted index, (6) save the data and website links to a text file, (7) be able to repeat the preceding functions for any number webpages, and finally (8) be able to return search results from user input. These are the main things and functionalities we must design a solution to implement into the program.</p>
<p>A simple, compact webpage was chosen as the seeder. The site will be “http://jsmooth.sourceforge.net/index.php.” This webpage may be simple, but it contains enough webpage links and data to supply our program's requirements. We added an option in the application interface to change the seeder if wanted. It is made up of a JTextField and Jbutton. The user just needs to input a website link and press the button. This is the same for the other only input for this program: the input of search terms. </p>
<p>This program's output is the TextArea in the program. After entering the search terms and pressing the button, the program will look through the entire inverted index of our database to find any match result. This program can intake multiple search terms. The time it takes to complete a search is independent of how massive our database is, because we can generate the hashcode from the search terms. Since this programming project is essentially making a search engine, we specially design the database to be stored in a mapping system with the key as the word, and the value to be the object called Bucket with the properties of location found and frequency count. A delicate ranking system will order the result of the search to descending of occurrences of words. If the search involves more than one term, it will rank the webpage link containing the highest number of terms in it, higher.  After being found and ranked, the program will finally output the result as a set of webpage links. The result went through a set to eliminate any duplicate webpage links.</p>
<p>The search process:	
	<ol><li>Let user input search term or terms</li>
		<li>Break the terms into a Set of single words</li>
		<li>Get the hashcode for each word. The hashcode will give us the indexes of all location the value is located at</li>
		<li>Get all the indexes' value (the Bucket class) into a Set</li>
		<li>Sort the set using the Bucket's frqCount descending</li>
		<li>Output all the docID from the Set</li>
	</ol>
</p>

<h2>Testing:</h2>
<ol><li>Open project in Netbeans. The project is named InformationRetrievalSystems<li>
	<li>Run InformationRetrievalSystems.java</li>
	<li>On the interface, press the “Build” button</li>
	<li>Once finishing building the data, enter a search term and press the “Search” button</li> 
	<li>Program is completed, you may close it now or use other features</li>
</ol>

<h2>Screen shots:</h2>
<p>Default View: <img src="https://github.com/netlams/website-crawler/blob/master/images/1.gui%20tested.png" alt="Gathering data"></p>
<p>Gathering data (web crawling) <img src="https://github.com/netlams/website-crawler/blob/master/images/2.building%20tested.png" alt="Gathering data"></p>
<p>Searching through data <img src="https://github.com/netlams/website-crawler/blob/master/images/7.search%20scenario1%20tested.png" alt="Searching through gathered data"></p>
