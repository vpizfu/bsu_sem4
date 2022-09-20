#!/usr/bin/env python3
# -*- coding: utf-8 -*-

import tests
from ciphers import Caesar, Vigenere
from hackers import Kasiski, Analyzer

LANG = 'en'
VIGENERE_KEY_WORD = 'TEST'

with open('text_{}.txt'.format(LANG)) as data_file:
    data = data_file.read()

vigenere = Vigenere(VIGENERE_KEY_WORD, LANG)
encrypted = vigenere.encrypt(data)
decrypted = vigenere.decrypt(encrypted)
print('\nVigenere:')
print(' data:\t\t{}'.format(data))
print(' ENCRYPTED:\t{}'.format(encrypted))
print(' DECRYPTED:\t{}'.format(decrypted))
print('-' * 300)

kasiski = Kasiski(4, LANG)
length = kasiski.get_len(encrypted)
print('\nKasiski:')
print(' keyword length:\t{}'.format(length))

analyzer = Analyzer(length, LANG)
keyword = analyzer.find_keyword(encrypted)
print(' found keyword:\t{}'.format(keyword.upper()))
print('-' * 300)

tests.run()
