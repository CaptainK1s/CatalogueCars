package com.example.catalogue;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Car> cars;
    private int currentIndex = 0;
    
    private ImageView mainImageView;
    private TextView titleTextView;
    private TextView descriptionTextView;
    private Button prevButton;
    private Button nextButton;
    private ImageButton[] thumbnailButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Set system bars to dark theme
        getWindow().setStatusBarColor(getResources().getColor(R.color.background_dark, getTheme()));
        getWindow().setNavigationBarColor(getResources().getColor(R.color.background_dark, getTheme()));
        
        setContentView(R.layout.activity_main);

        // Initialize views
        mainImageView = findViewById(R.id.mainImageView);
        titleTextView = findViewById(R.id.titleTextView);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        prevButton = findViewById(R.id.prevButton);
        nextButton = findViewById(R.id.nextButton);

        // Initialize thumbnail buttons
        thumbnailButtons = new ImageButton[]{
            findViewById(R.id.thumbnail1),
            findViewById(R.id.thumbnail2),
            findViewById(R.id.thumbnail3),
            findViewById(R.id.thumbnail4)
        };

        // Initialize car data
        initializeCars();

        // Set click listeners
        prevButton.setOnClickListener(v -> showPreviousCar());
        nextButton.setOnClickListener(v -> showNextCar());

        // Set thumbnail click listeners
        for (int i = 0; i < thumbnailButtons.length; i++) {
            final int index = i;
            thumbnailButtons[i].setOnClickListener(v -> showCar(index));
        }

        // Show initial car
        updateDisplay();
    }

    private void initializeCars() {
        cars = new ArrayList<>();
        cars.add(new Car(
            "BMW M4 Competition",
            "The BMW M4 Competition is a perfect harmony of precision engineering, breathtaking power, and cutting-edge technology. Under the hood lies a twin-turbocharged 3.0-liter inline-six engine, producing a jaw-dropping 503 horsepower and 479 lb-ft of torque. This powerhouse launches the M4 from 0 to 60 mph in just 3.8 seconds, making it a true track weapon.\n" +
                    "\n" +
                    "The car’s aggressive design is unmistakable, featuring its bold kidney grille, sculpted hood, and aerodynamic lines that scream performance. Inside, the M4 Competition pampers its occupants with premium leather seats, carbon fiber accents, and a fully digital cockpit loaded with BMW’s latest iDrive system.\n" +
                    "\n" +
                    "Whether you're carving through winding mountain roads or cruising through the city, the M4 offers impeccable handling, thanks to its adaptive M suspension, rear-wheel drive (or optional xDrive all-wheel drive), and precise steering. It's not just a car—it’s an experience for those who crave the thrill of driving.",
            R.drawable.bmwm4c,
            R.drawable.bmwm4c
        ));
        cars.add(new Car(
            "Audi RS7 Sportback",
            "The Audi RS7 Sportback is the epitome of power, luxury, and versatility, wrapped in a sleek four-door coupe body. Beneath its elegant yet aggressive exterior lies a 4.0-liter twin-turbo V8 engine that produces a staggering 591 horsepower and 590 lb-ft of torque. This engineering marvel rockets the RS7 from 0 to 60 mph in a mere 3.5 seconds, delivering supercar performance in a practical package.\n" +
                    "\n" +
                    "Step inside, and you’re greeted with Audi’s signature attention to detail—quilted leather seats, ambient lighting, and a dual-screen MMI infotainment system with haptic feedback. The spacious cabin comfortably seats five, making it perfect for long road trips or daily commutes.\n" +
                    "\n" +
                    "The RS7 also comes equipped with Audi’s Quattro all-wheel-drive system, providing unparalleled grip and control on any terrain. Whether you’re tearing down the autobahn or gliding through city streets, the RS7 offers a driving experience that’s both exhilarating and refined.",
            R.drawable.audirs7,
            R.drawable.audirs7
        ));
        cars.add(new Car(
            "Porsche 911 ST",
            "The Porsche 911 ST is a celebration of Porsche’s iconic motorsport legacy, designed to thrill purists and enthusiasts alike. At its heart is a naturally aspirated 4.0-liter flat-six engine, borrowed from the 911 GT3, that revs up to 9,000 RPM and delivers an exhilarating 518 horsepower. Paired with a six-speed manual transmission, the 911 ST offers a visceral connection between car and driver that’s hard to find in today’s world of automated performance.\n" +
                    "\n" +
                    "Its lightweight construction, featuring extensive use of carbon fiber and magnesium, ensures razor-sharp handling and exceptional agility on twisty roads or the track. The car’s design is timeless, with a nod to Porsche’s racing heritage, blending modern aerodynamics with classic 911 curves.\n" +
                    "\n" +
                    "Inside, the cockpit is minimalist yet luxurious, with Alcantara finishes, sport bucket seats, and analog gauges that reinforce its driver-focused nature. The Porsche 911 ST is more than just a car—it’s a masterpiece of engineering and a tribute to pure driving joy.",
            R.drawable.porsche,
            R.drawable.porsche
        ));
        cars.add(new Car(
            "Range Rover SV Autobiography",
            "The Range Rover SV Autobiography redefines the luxury SUV segment with its unmatched blend of refinement, capability, and craftsmanship. As the flagship model of the Range Rover lineup, it boasts a 5.0-liter supercharged V8 engine that generates a commanding 557 horsepower, effortlessly propelling this luxury behemoth to 60 mph in just 5.1 seconds.\n" +
                    "\n" +
                    "Every detail of the SV Autobiography has been meticulously crafted to deliver an unparalleled experience. The interior is a sanctuary of opulence, featuring hand-stitched leather upholstery, wood veneer accents, and reclining rear seats with built-in massagers. A full-length panoramic sunroof bathes the cabin in natural light, while the Meridian sound system creates a concert-like atmosphere.\n" +
                    "\n" +
                    "Despite its luxury-focused demeanor, the SV Autobiography retains Range Rover’s legendary off-road capability, with adjustable air suspension, Terrain Response 2, and all-wheel drive. Whether you’re navigating urban streets or exploring rugged trails, this SUV offers a smooth, commanding ride that few can rival. It’s the ultimate vehicle for those who demand the best of both worlds: luxury and adventure.",
            R.drawable.rangeroversv,
            R.drawable.rangeroversv
        ));
    }

    private void showPreviousCar() {
        currentIndex = (currentIndex - 1 + cars.size()) % cars.size();
        animateImageTransition(false);
        updateDisplay();
    }

    private void showNextCar() {
        currentIndex = (currentIndex + 1) % cars.size();
        animateImageTransition(true);
        updateDisplay();
    }

    private void showCar(int index) {
        if (index != currentIndex) {
            boolean forward = index > currentIndex;
            currentIndex = index;
            animateImageTransition(forward);
            updateDisplay();
        }
    }

    private void updateDisplay() {
        Car currentCar = cars.get(currentIndex);
        
        // Update main display
        mainImageView.setImageResource(currentCar.getImageResourceId());
        titleTextView.setText(currentCar.getName());
        descriptionTextView.setText(currentCar.getDescription());

        // Update thumbnails
        for (int i = 0; i < thumbnailButtons.length; i++) {
            thumbnailButtons[i].setImageResource(cars.get(i).getThumbnailResourceId());
            thumbnailButtons[i].setAlpha(i == currentIndex ? 1.0f : 0.6f);
        }

        // Update button states
        prevButton.setEnabled(true);
        nextButton.setEnabled(true);
    }

    private void animateImageTransition(boolean forward) {
        // Fade out
        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(mainImageView, "alpha", 1f, 0f);
        fadeOut.setDuration(150);
        
        // Slide out
        float startX = forward ? 0f : 0f;
        float endX = forward ? -mainImageView.getWidth() : mainImageView.getWidth();
        ObjectAnimator slideOut = ObjectAnimator.ofFloat(mainImageView, "translationX", startX, endX);
        slideOut.setDuration(150);

        // Fade in
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(mainImageView, "alpha", 0f, 1f);
        fadeIn.setDuration(150);
        
        // Slide in
        float startXIn = forward ? mainImageView.getWidth() : -mainImageView.getWidth();
        ObjectAnimator slideIn = ObjectAnimator.ofFloat(mainImageView, "translationX", startXIn, 0f);
        slideIn.setDuration(150);

        // Set interpolator for smooth animation
        fadeOut.setInterpolator(new AccelerateDecelerateInterpolator());
        fadeIn.setInterpolator(new AccelerateDecelerateInterpolator());
        slideOut.setInterpolator(new AccelerateDecelerateInterpolator());
        slideIn.setInterpolator(new AccelerateDecelerateInterpolator());

        // Run animations in sequence
        fadeOut.start();
        slideOut.start();
        fadeOut.setAutoCancel(true);
        slideOut.setAutoCancel(true);

        mainImageView.postDelayed(() -> {
            fadeIn.start();
            slideIn.start();
            fadeIn.setAutoCancel(true);
            slideIn.setAutoCancel(true);
        }, 150);
    }
}