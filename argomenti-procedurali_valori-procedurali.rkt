;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname argomenti-procedurali_valori-procedurali) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
;cifrario di cesare
;argomenti procedurali

(define enc ;val: stringa criptata
  (lambda (msg rule) ;msg: stringa alfabeto maiuscolo rule: procedura [char -> char criptato]
    (if (string=? msg "")
        ""
        (string-append (string (rule (string-ref msg 0))) (enc (substring msg 1) rule))
        )
    ))

;(enc "QUALCOSA" (lambda (c) c))

;;regola di cesare
(define rot-3
  (lambda (c)
    (let (( k (+ (char->integer c) 3))
          )
      (if (<= k pos-Z)
          (integer->char k)
          (integer->char (- k 26))
          )
      )
    ))

(define pos-A (char->integer #\A))
(define pos-I (char->integer #\I))
(define pos-T (char->integer #\T))
(define pos-V (char->integer #\V))
(define pos-X (char->integer #\X))
(define pos-Z (char->integer #\Z))
(define pos-Y (char->integer #\Y))

;(enc "QUALCOSA" rot-3)

;valori procedurali
(define rot
  (lambda (R)
    (lambda (c)
      (let ((k (+ (char->integer c) R))
            )
        (if (<= k pos-Z)
            (integer->char k)
            (integer->char (- k 26))
            )
        )
      )))

(define alfabeto-latino "ABCDEFGHILMNOPQRSTVX")

(define enc-lat ; val:stringa
  (lambda (msg rule) ; msg:stringa, rule:procedura [char] -> [char]
      (cond ((string=? msg "")
             "")
            ((string=? (substring msg 0 1) " ")
             (string-append
              ""
              (enc-lat (substring msg 1) rule)      
              ))
            ((presente? (substring msg 0 1) alfabeto-latino)
             (string-append
              (string (rule (string-ref msg 0)))
              (enc-lat (substring msg 1) rule)      
              ))
            (else
             (string-append
              " [Carattere non Latino!] "
              (enc-lat (substring msg 1) rule)      
              ))
            )
    ))

(define rot-lat ; val: char (lettera maiuscola)
  (lambda (R) ; R:intero

    (lambda (c)  ; c:char(lettera maiuscola)
      (let ((pos-rot (+ (position c alfabeto-latino) R))
            )
        (if (<= pos-rot (- (string-length alfabeto-latino) 1)) 
            (string-ref alfabeto-latino pos-rot)               
            (string-ref alfabeto-latino (- pos-rot 20)))       
        ))
    ))

(define position ; val:intero --> indice di una lettera all'interno dell'alfabeto
  (lambda (c alf) ; c:char, alf:stringa
    (if (char=? c (string-ref alf 0))
        0
        (+ 1 (position c (substring alf 1))))
    ))

(define presente? ; val:booleano --> il carattere è latino o no? 
  (lambda (c alf) ; c:char, alf: stringa
       (cond ((string=? alf "")
              false)
             ((string=? c (substring alf 0 1))
              true)
             (else
              (presente? c (substring alf 1)))
             )
    ))

(define dec ;val: procedura [char->char decriptato]
  (lambda (rule) ;rule: procedura [char->char criptato]
    (let ((R (- (char->integer (rule #\A)) pos-A))
          )
      (rot (- 26 R))
      )
    ))

(define inv ;val: procedura [char->char decriptato]
  (lambda (rule) ;rule: procedura [char->char criptato]
    (lambda (c) ;c : char
      (find pos-A c rule)
      )))

(define find ;val: char
  (lambda (x c rule) ;x: intero posizione, c: char, rule: procedura [char->char criptato]
    (if (char=? (rule (integer->char x)) c)
        (integer->char x)
        (find (+ x 1) c rule)
        )
    ))