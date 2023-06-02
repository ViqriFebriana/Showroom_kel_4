package id.ac.unpas.showroom.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benasher44.uuid.uuid4
import id.ac.unpas.showroom.model.mobil
import id.ac.unpas.showroom.persistences.MobilDao
import id.ac.unpas.showroom.ui.theme.Purple700
import id.ac.unpas.showroom.ui.theme.Teal200
import kotlinx.coroutines.launch

@Composable
fun FormMobil(mobilDao : MobilDao) {
    val merk = remember { mutableStateOf(TextFieldValue("")) }
    val model = remember { mutableStateOf(TextFieldValue("")) }
    val bahan_bakar = remember { mutableStateOf(TextFieldValue("")) }
    val dijual = remember { mutableStateOf(TextFieldValue("")) }
    val deskripsi= remember { mutableStateOf(TextFieldValue("")) }

    val scope = rememberCoroutineScope()

    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()) {
        OutlinedTextField(
            label = { Text(text = "merk") },
            value = merk.value,
            onValueChange = {
                merk.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            placeholder = { Text(text = "merk mobil") }
        )
        OutlinedTextField(
            label = { Text(text = "model") },
            value = model.value,
            onValueChange = {
                model.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            keyboardOptions = KeyboardOptions(capitalization =
            KeyboardCapitalization.Characters, keyboardType = KeyboardType.Text),
            placeholder = { Text(text = "model mobil") }
        )
        OutlinedTextField(
            label = { Text(text = "bahan bakar") },
            value = bahan_bakar.value,
            onValueChange = {
                bahan_bakar.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType =
            KeyboardType.Decimal),
            placeholder = { Text(text = "bahan bakar mobil") }
        )
        OutlinedTextField(
            label = { Text(text = "dijual") },
            value = dijual.value,
            onValueChange = {
                dijual.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType =
            KeyboardType.Decimal),
            placeholder = { Text(text = "mobil di jual") }
        )
//        di jual menggunakan bolean
        OutlinedTextField(
            label = { Text(text = "deskripsi") },
            value = deskripsi.value,
            onValueChange = {
                deskripsi.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType =
            KeyboardType.Decimal),
            placeholder = { Text(text = "deskripsi mobil") }
        )
        val loginButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Purple700,
            contentColor = Teal200
        )
        val resetButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Teal200,
            contentColor = Purple700
        )
        Row (modifier = Modifier.padding(4.dp).fillMaxWidth()) {
            Button(modifier = Modifier.weight(5f), onClick = {
                val id = uuid4().toString()
                val item = mobil(merk.value.text, model.value.text,
                    bahan_bakar.value.text,dijual.value.text,deskripsi.value.text)
                scope.launch {
                    mobilDao.insertAll(item)
                }
                merk.value = TextFieldValue("")
                model.value = TextFieldValue("")
                bahan_bakar.value = TextFieldValue("")
                dijual.value = TextFieldValue("")
                deskripsi.value = TextFieldValue("")
            }, colors = loginButtonColors) {
                Text(
                    text = "Simpan",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
            Button(modifier = Modifier.weight(5f), onClick = {
                merk.value = TextFieldValue("")
                model.value = TextFieldValue("")
                bahan_bakar.value = TextFieldValue("")
                dijual.value = TextFieldValue("")
                deskripsi.value = TextFieldValue("")
            }, colors = resetButtonColors) {
                Text(
                    text = "Reset",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}
