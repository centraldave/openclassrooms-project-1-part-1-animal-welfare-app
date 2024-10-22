package com.animals.safety.screens

import android.graphics.Paint.Align
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.animals.safety.R
import com.animals.safety.data.Animal
import com.animals.safety.data.Breed
import com.animals.safety.ui.theme.AimantsDanimauxTheme
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimalDetailsScreen(
  modifier: Modifier = Modifier,
  animal: Animal,
  onBackClick: () -> Unit,
) {
  Scaffold(
    modifier = modifier,
    topBar = {
      TopAppBar(
        title = {
          Text(stringResource(id = R.string.details_fragment_label))
        },
        navigationIcon = {
          IconButton(onClick = {
            onBackClick()
          }) {
            Icon(
              imageVector = Icons.AutoMirrored.Filled.ArrowBack,
              contentDescription = stringResource(id = R.string.contentDescription_go_back)
            )
          }
        }
      )
    },
  ) { contentPadding ->
    AnimalDetails(
      modifier = modifier.padding(contentPadding),
      animal = animal,
    )
  }
}

@Composable
private fun AnimalDetails(
  modifier: Modifier = Modifier,
  animal: Animal,
) {
  //TODO: A compléter
  Column (
    modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Row (
      modifier = Modifier.weight(1f)
    ) {
        Box (
          contentAlignment = Alignment.Center,
          modifier = Modifier
            .fillMaxWidth()
        ) {
          Image(
            painter = painterResource(id = animal.breed.cover), // Replace with your image resource
            contentDescription = "Description of the image",
            modifier = Modifier
              .fillMaxSize(),
            contentScale = ContentScale.Crop
          )

          Text( text = animal.name,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
              .align(Alignment.BottomStart)
              .padding(16.dp),
            color = Color.White
          )
        }
    }

    Spacer(modifier = Modifier.height(50.dp))

    Row (
      modifier = Modifier.weight(1f)
    ) {
      Column {
        Row {
          Column {
            AnimalDetailLabel(
              R.drawable.ic_age,
              stringResource(R.string.hint_age),
              "${animal.age} years old"
            )
          }

          Column {
            AnimalDetailLabel(
              R.drawable.ic_weight,
              stringResource(R.string.hint_weight),
              "${animal.weight} (Kg)"
            )
          }
        }

        Row (
          modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
          Column {
            AnimalDetailLabel(
              R.drawable.ic_height,
              stringResource(R.string.hint_height),
              "${animal.height} (cm)"
            )
          }
        }
      }
    }
  }
}

@Composable
fun AnimalDetailLabel(
  @DrawableRes image: Int,
  imageDescription: String,
  labelText: String
) {
  Box {
    Column (
      modifier = Modifier
        .padding(16.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Row {
        Image(
          painter = painterResource(id = image), // Replace with your image resource
          contentDescription = imageDescription,
          modifier = Modifier
            .height(50.dp),
          colorFilter = ColorFilter.tint(Color.Black),
          contentScale = ContentScale.Crop
        )
      }

      Row (
        modifier = Modifier
          .padding(16.dp)
      ) {
        Text( text = labelText,
          style = MaterialTheme.typography.labelLarge
        )
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
private fun AnimalDetailsPreview() {
  AimantsDanimauxTheme(dynamicColor = false) {
    AnimalDetails(
      animal = Animal(UUID.randomUUID(),"Milou", Breed.DOG, 6, 23.2f, 42.4f),
    )
  }
}