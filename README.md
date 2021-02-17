# ASCII Art Generator

[![pipeline status](https://gitlab.fit.cvut.cz/alekhiva/ascii-art-generator/badges/master/pipeline.svg)](https://gitlab.fit.cvut.cz/alekhiva/ascii-art-generator)

![Scala logo](https://i.imgur.com/WxeADKa.png)

In this project i use **Scala** programming language to implement loading images and translating them into ASCII Art and, optionally, applying filters.

Example commands:
> run --image ../images/test-image.jpg --rotate +90 --scale 0.25 --invert --output-console

> run --image test-image.jpg --output-file ../outputs/output.txt

> run --image test-image.jpg --rotate +89 --invert --rotate +1 --output-file ../outputs/output.txt --output-console 

## Usage

### 1. Loading image
> --image *image-path*

### 2. Applying filters
2a. Rotating 
> --rotate *degree*

2b. Scaling 
> --scale *amount*

2c. Inverting color
> --invert

### 3. Outputting result
> --output-file *file-path*

> --output-console

## Result

![Result](https://i.imgur.com/OplfjfL.png)

