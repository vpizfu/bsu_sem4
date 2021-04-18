import os

from PIL import Image
from tabulate import tabulate

# Перевод для считывания глуюины цвета
COLOR_DEPTH = {'1': 1, 'L': 8, 'P': 8, 'RGB': 24, 'RGBA': 32, 'CMYK': 32, 'YCbCr': 24, 'I': 32, 'F': 32}


def get_image_info(image_path):
    image = Image.open(image_path)
    image.load()
    return { 'img_name': os.path.basename(image_path), 'width': image.size[0], 'height': image.size[1], 'image_mode': image.mode, 'color_dph': COLOR_DEPTH.get(image.mode, 'Unknown'), 'dpi': image.info.get('dpi', 'Unknown'), 'compression': image.info.get('compression', 'Unknown'), }

if __name__ == "__main__":
    images_info = [get_image_info(image_entry.path) for image_entry in os.scandir(input('Введите полный путь до папки с ассетом фоток: '))]
    print(tabulate([image_info.values() for image_info in images_info], headers=['Name', 'Width', 'Height', 'Image Mode', 'Color Depth', 'DPI', 'Compression']))
