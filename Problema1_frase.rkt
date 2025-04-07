;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname Problema1_frase) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
(define frase ;val: stringa
  (lambda (sogg verb compl) ;sogg, verb, compl: stringa
    (string-append (articolo sogg) sogg " " (cognug verb sogg) (articolo compl) compl)
    ))

(define articolo ;val: stringa il, i; la, le
  (lambda (sogg) ;sogg: stringa
    (if (or (maschile sogg) (string=? (substring sogg (- (string-length sogg) 1)) "i"))
        (if (singolare sogg)
        "il "
        "i "
        )
        (if (singolare sogg)
            "la "
            "le "
            )
        )
    ))

(define singolare ;val: string
  (lambda (s)     ;s: string
    (if (maschile s)
        (string=? (substring s (- (string-length s) 1)) "o")
        (string=? (substring s (- (string-length s) 1)) "a")
        )
    ))

(define maschile ;val: boolean
  (lambda (s) ;s: stringa
    (if (string=? (substring s (- (string-length s) 1)) "o")
        true
        false
        )
    ))

(define cognug ; val: string
  (lambda (s s1) ;s, s1: string
    (if (string=? (substring s (- (string-length s) 3)) "are")
        (if (singolare s1)
            (string-append (substring s 0 (- (string-length s) 3)) "a ")
            (string-append (substring s 0 (- (string-length s) 2)) "no ")
            )
        (if (singolare s1)
            (string-append (substring s 0 (- (string-length s) 3)) "e ")
            (string-append (substring s 0 (- (string-length s) 3)) "ono ")
            )
        )
    ))

