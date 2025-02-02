# Bitcoin Network Information Tool

A Java-based application that connects to a Bitcoin node to provide detailed information about the Bitcoin network. With this tool, you can search for specific blocks and transactions, view network statistics, and much more.

<br>

---

## ✨ Features

1. 🔍 **Search a Block**  
   Retrieve details about a specific block using its hash or height.

2. 🔍 **Search a Transaction**  
   Look up a transaction by its transaction ID (TXID) to view its details.

3. 📊 **Display Network Statistics**  
   Get an overview of the Bitcoin network, including block height, difficulty, and more.

4. 📥 **Display Mempool Transactions**  
   View pending transactions waiting to be confirmed.

5. ⛏️ **Display Mining Information**  
   Get insights into mining statistics, such as difficulty and hash rate.

6. 🔗 **Display Node Connected Information**  
   Check details about the node you're connected to, including peer connections and version.
<br>

---


## 🛠️ Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/bitcoin-network-info.git
   cd bitcoin-network-info

<br>

---


## ⚙️ Node Configuration


To enable the program to retrieve information from the Bitcoin network, you need to configure the Bitcoin Node connection:

1. Open the `BlockchainManager.java` file and update the following variables with your Bitcoin node's credentials:
   <br>
   
	```java
	private static final String user = "your_user";
	private static final String password = "your_password";
	private static final String host = "your_node_host";
	private static final String port = "8332";

**Important**: The Bitcoin node you connect do must support JSON-RPC connections.
