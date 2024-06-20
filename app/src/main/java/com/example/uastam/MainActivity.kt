package com.example.uastam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.Image
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.uastam.ui.theme.UASTAMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UASTAMTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("jadwal_piket") { JadwalPiketScreen(navController) }
        composable("kamar_tahanan") { KamarTahananScreen(navController) }
        composable("kegiatan_bermanfaat") { KegiatanBermanfaatScreen(navController) }
        composable("kontrol_makanan") { KontrolMakananScreen(navController) }
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.bgtam2),
                contentDescription = "Background",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                val buttonModifier = Modifier
                    .fillMaxWidth(0.8f) // Use 80% of the parent's width
                    .height(48.dp) // Set a fixed height for the buttons

                val buttonColors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                )

                Button(
                    onClick = { navController.navigate("kontrol_makanan") },
                    modifier = buttonModifier,
                    colors = buttonColors
                ) {
                    Text("Kontrol Makanan")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { navController.navigate("jadwal_piket") },
                    modifier = buttonModifier,
                    colors = buttonColors
                ) {
                    Text("Jadwal Piket")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { navController.navigate("kegiatan_bermanfaat") },
                    modifier = buttonModifier,
                    colors = buttonColors
                ) {
                    Text("Kegiatan Bermanfaat")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { navController.navigate("kamar_tahanan") },
                    modifier = buttonModifier,
                    colors = buttonColors
                ) {
                    Text("Kamar Tahanan")
                }
            }
        }
    }
}

@Composable
fun KontrolMakananScreen(navController: NavController) {
    var isDialogOpen by remember { mutableStateOf(false) }
    var selectedCell by remember { mutableStateOf<Pair<Int, Int>?>(null) }
    val jadwalMakan = remember {
        mutableStateListOf(
            mutableListOf("Senin", "08:00", "Nasi Goreng", "13:00", "Ayam Goreng", "19:00", "Sate"),
            mutableListOf("Selasa", "08:00", "Mie Goreng", "13:00", "Rendang", "19:00", "Bakso"),
            mutableListOf("Rabu", "08:00", "Soto Ayam", "13:00", "Ikan Bakar", "19:00", "Nasi Kuning"),
            mutableListOf("Kamis", "08:00", "Nasi Uduk", "13:00", "Gulai Ayam", "19:00", "Sop Buntut"),
            mutableListOf("Jumat", "08:00", "Bubur Ayam", "13:00", "Sop Iga", "19:00", "Pecel Lele"),
            mutableListOf("Sabtu", "08:00", "Nasi Uduk", "13:00", "Ayam Bakar", "19:00", "Sate Ayam"),
            mutableListOf("Minggu", "08:00", "Nasi Lemak", "13:00", "Ayam Penyet", "19:00", "Gado-gado")
        )
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.bgtam8),
                contentDescription = "Background",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.align(Alignment.Start)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_back),
                        contentDescription = "Back"
                    )
                }
                Text(
                    text = "Jadwal Makan",
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        shadow = Shadow(
                            color = Color.Black,
                            offset = Offset(4f, 4f),
                            blurRadius = 4f
                        )
                    ),
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                LazyColumn {
                    items(jadwalMakan) { row ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .shadow(2.dp)
                                .clickable {
                                    selectedCell = jadwalMakan.indexOf(row) to -1
                                    isDialogOpen = true
                                },
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surface,
                                contentColor = MaterialTheme.colorScheme.onSurface
                            )
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                Text(
                                    text = row[0],
                                    style = MaterialTheme.typography.titleMedium,
                                    modifier = Modifier.padding(bottom = 8.dp)
                                )
                                for (i in 1 until row.size step 2) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(4.dp)
                                            .clickable {
                                                selectedCell = jadwalMakan.indexOf(row) to i
                                                isDialogOpen = true
                                            }
                                    ) {
                                        Text(
                                            text = row[i],
                                            style = MaterialTheme.typography.bodyMedium,
                                            modifier = Modifier.weight(1f)
                                        )
                                        Text(
                                            text = row[i + 1],
                                            style = MaterialTheme.typography.bodyMedium,
                                            modifier = Modifier.weight(2f)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    if (isDialogOpen && selectedCell != null) {
        val (rowIndex, columnIndex) = selectedCell!!
        val initialValue = jadwalMakan[rowIndex][columnIndex]
        var textFieldValue by remember { mutableStateOf(TextFieldValue(initialValue)) }

        AlertDialog(
            onDismissRequest = { isDialogOpen = false },
            confirmButton = {
                Button(
                    onClick = {
                        jadwalMakan[rowIndex][columnIndex] = textFieldValue.text
                        isDialogOpen = false
                    }
                ) {
                    Text("Simpan")
                }
            },
            dismissButton = {
                Button(onClick = { isDialogOpen = false }) {
                    Text("Batal")
                }
            },
            title = { Text("Edit Nama") },
            text = {
                TextField(
                    value = textFieldValue,
                    onValueChange = { textFieldValue = it },
                    label = { Text("Nama") }
                )
            }
        )
    }
}

@Composable
fun JadwalPiketScreen(navController: NavController) {
    var isDialogOpen by remember { mutableStateOf(false) }
    var selectedCell by remember { mutableStateOf<Pair<Int, Int>?>(null) }
    val jadwalPiket = remember {
        mutableStateListOf(
            mutableListOf("Hari", "Kamar"),
            mutableListOf("Senin", "Kamar No 2, Kamar No 3"),
            mutableListOf("Selasa", "Kamar No 1, Kamar No 4"),
            mutableListOf("Rabu", "Kamar No 5, Kamar No 6"),
            mutableListOf("Kamis", "Kamar No 7, Kamar No 8"),
            mutableListOf("Jumat", "Kamar No 9, Kamar No 10"),
            mutableListOf("Sabtu", "Kamar No 11, Kamar No 12"),
            mutableListOf("Minggu", "Kamar No 13, Kamar No 14")
        )
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.bgtam8),
                contentDescription = "Background",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.align(Alignment.Start)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_back),
                        contentDescription = "Back"
                    )
                }
                Text(
                    text = "Jadwal Piket",
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        shadow = Shadow(
                            color = Color.Black,
                            offset = Offset(4f, 4f),
                            blurRadius = 4f
                        )
                    ),
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Table(
                    rows = jadwalPiket,
                    onCellClick = { rowIndex, columnIndex ->
                        selectedCell = rowIndex to columnIndex
                        isDialogOpen = true
                    }
                )
            }
        }
    }

    if (isDialogOpen && selectedCell != null) {
        val (rowIndex, columnIndex) = selectedCell!!
        val initialValue = jadwalPiket[rowIndex][columnIndex]
        var textFieldValue by remember { mutableStateOf(TextFieldValue(initialValue)) }

        AlertDialog(
            onDismissRequest = { isDialogOpen = false },
            confirmButton = {
                Button(
                    onClick = {
                        jadwalPiket[rowIndex][columnIndex] = textFieldValue.text
                        isDialogOpen = false
                    }
                ) {
                    Text("Simpan")
                }
            },
            dismissButton = {
                Button(onClick = { isDialogOpen = false }) {
                    Text("Batal")
                }
            },
            title = { Text("Edit Nama") },
            text = {
                TextField(
                    value = textFieldValue,
                    onValueChange = { textFieldValue = it },
                    label = { Text("Nama") }
                )
            }
        )
    }
}

@Composable
fun KamarTahananScreen(navController: NavController) {
    val kamarList = remember {
        mutableStateListOf(
            "Kamar No 1" to mutableStateListOf("Tahanan A", "Tahanan B", "Tahanan C", "Tahanan D", "Tahanan E"),
            "Kamar No 2" to mutableStateListOf("Tahanan F", "Tahanan G", "Tahanan H", "Tahanan I", "Tahanan J"),
            "Kamar No 3" to mutableStateListOf("Tahanan K", "Tahanan L", "Tahanan M", "Tahanan N", "Tahanan O"),
            "Kamar No 4" to mutableStateListOf("Tahanan P", "Tahanan Q", "Tahanan R", "Tahanan S", "Tahanan T"),
            "Kamar No 5" to mutableStateListOf("Tahanan U", "Tahanan V", "Tahanan W", "Tahanan X", "Tahanan Y"),
            "Kamar No 6" to mutableStateListOf("Tahanan Z", "Tahanan AA", "Tahanan BB", "Tahanan CC", "Tahanan DD"),
            "Kamar No 7" to mutableStateListOf("Tahanan EE", "Tahanan FF", "Tahanan GG", "Tahanan HH", "Tahanan II")
        )
    }

    var isDialogOpen by remember { mutableStateOf(false) }
    var selectedCell by remember { mutableStateOf<Pair<Int, Int>?>(null) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.bgtam8),
                contentDescription = "Background",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.align(Alignment.Start)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_back),
                        contentDescription = "Back"
                    )
                }
                Text(
                    text = "Kamar Tahanan",
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        shadow = Shadow(
                            color = Color.Black,
                            offset = Offset(4f, 4f),
                            blurRadius = 4f
                        )
                    ),
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                LazyColumn {
                    items(kamarList) { (kamar, tahananList) ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .shadow(2.dp)
                                .clickable {
                                    // Set the selected cell and open the dialog
                                    selectedCell = kamarList.indexOfFirst { it.first == kamar } to -1
                                    isDialogOpen = true
                                },
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surface,
                                contentColor = MaterialTheme.colorScheme.onSurface
                            )
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                Text(
                                    text = kamar,
                                    style = MaterialTheme.typography.titleMedium,
                                    modifier = Modifier.padding(bottom = 8.dp)
                                )
                                tahananList.forEachIndexed { index, tahanan ->
                                    Text(
                                        text = tahanan,
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier
                                            .padding(start = 16.dp)
                                            .clickable {
                                                // Set the selected cell and open the dialog
                                                selectedCell = kamarList.indexOfFirst { it.first == kamar } to index
                                                isDialogOpen = true
                                            }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    if (isDialogOpen && selectedCell != null) {
        val (kamarIndex, tahananIndex) = selectedCell!!
        val initialValue = if (tahananIndex == -1) kamarList[kamarIndex].first else kamarList[kamarIndex].second[tahananIndex]
        var textFieldValue by remember { mutableStateOf(TextFieldValue(initialValue)) }

        AlertDialog(
            onDismissRequest = { isDialogOpen = false },
            confirmButton = {
                Button(
                    onClick = {
                        if (tahananIndex == -1) {
                            val tahananList = kamarList[kamarIndex].second
                            kamarList[kamarIndex] = textFieldValue.text to tahananList
                        } else {
                            kamarList[kamarIndex].second[tahananIndex] = textFieldValue.text
                        }
                        isDialogOpen = false
                    }
                ) {
                    Text("Simpan")
                }
            },
            dismissButton = {
                Button(onClick = { isDialogOpen = false }) {
                    Text("Batal")
                }
            },
            title = { Text("Edit Nama") },
            text = {
                TextField(
                    value = textFieldValue,
                    onValueChange = { textFieldValue = it },
                    label = { Text("Nama") }
                )
            }
        )
    }
}

@Composable
fun KegiatanBermanfaatScreen(navController: NavController) {
    val kegiatanList = remember {
        mutableStateListOf(
            mutableListOf("Hari", "Kegiatan 1", "Kegiatan 2"),
            mutableListOf("Senin", "Senam", "Rohani"),
            mutableListOf("Selasa", "Senam", "Bermusik"),
            mutableListOf("Rabu", "Senam", "Berenang"),
            mutableListOf("Kamis", "Senam", "Belajar"),
            mutableListOf("Jumat", "Senam", "Diskusi"),
            mutableListOf("Sabtu", "Senam", "Main Bola"),
            mutableListOf("Minggu", "Senam", "Berkebun")
        )
    }

    var isDialogOpen by remember { mutableStateOf(false) }
    var selectedCell by remember { mutableStateOf<Pair<Int, Int>?>(null) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.bgtam8),
                contentDescription = "Background",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.align(Alignment.Start)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_back),
                        contentDescription = "Back"
                    )
                }
                Text(
                    text = "Kegiatan Bermanfaat",
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        shadow = Shadow(
                            color = Color.Black,
                            offset = Offset(4f, 4f),
                            blurRadius = 4f
                        )
                    ),
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Table(
                    rows = kegiatanList,
                    onCellClick = { rowIndex, columnIndex ->
                        selectedCell = rowIndex to columnIndex
                        isDialogOpen = true
                    }
                )
            }
        }
    }

    if (isDialogOpen && selectedCell != null) {
        val (rowIndex, columnIndex) = selectedCell!!
        val initialValue = kegiatanList[rowIndex][columnIndex]
        var textFieldValue by remember { mutableStateOf(TextFieldValue(initialValue)) }

        AlertDialog(
            onDismissRequest = { isDialogOpen = false },
            confirmButton = {
                Button(
                    onClick = {
                        kegiatanList[rowIndex][columnIndex] = textFieldValue.text
                        isDialogOpen = false
                    }
                ) {
                    Text("Simpan")
                }
            },
            dismissButton = {
                Button(onClick = { isDialogOpen = false }) {
                    Text("Batal")
                }
            },
            title = { Text("Edit Nama") },
            text = {
                TextField(
                    value = textFieldValue,
                    onValueChange = { textFieldValue = it },
                    label = { Text("Nama") }
                )
            }
        )
    }
}

@Composable
fun Table(
    modifier: Modifier = Modifier,
    rows: List<List<String>>,
    onCellClick: (Int, Int) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(rows.size) { rowIndex ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .shadow(2.dp)
                    .clickable { onCellClick(rowIndex, -1) },
                colors = CardDefaults.cardColors(
                    containerColor = if (rowIndex == 0) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                    contentColor = if (rowIndex == 0) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    rows[rowIndex].forEachIndexed { columnIndex, cell ->
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .padding(8.dp)
                                .clickable { onCellClick(rowIndex, columnIndex) },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = cell,
                                style = if (rowIndex == 0) MaterialTheme.typography.bodyLarge else MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.padding(4.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    UASTAMTheme {
        MyApp()
    }
}
