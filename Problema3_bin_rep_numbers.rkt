;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname Problema3_bin_rep_numbers) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss" "installed-teachpacks")) #f)))
(define bin-rep->number; val: int
  (lambda (n) ;n: string
         (cond ((string=? (substring n 0 1) "+")
                (calcolo-valore-bin (substring n 1)))
               ((string=? (substring n 0 1) "-")
                (* (calcolo-valore-bin (substring n 1)) -1))
               (else
                (calcolo-valore-bin n))
               )
    ))

(define calcolo-valore-bin ;val: int
  (lambda (n) ;n: string
    (let ((s (posizionepunto n))
            )
      (if (= s -1)
          (intera n);non ho il punto --- intera n
          (+ (intera (substring n 0 s)) (frazionaria (substring n (+ s 1))));ho il punto --- intera da primo elemento a quello prima del punto + frazionaria da elemento dopo il punto in poi
          )
      )
    ))
          

(define segno ;val: string
  (lambda (n) ;n: string
    (cond ((string=? (substring  n 0 1) "+")
           "0")
          ((or (string=? (substring  n 0 1) "1") (string=? (substring  n 0 1) "0"))
           n)
          (else
           "1")
        )
    ))

(define intera ;val: int
  (lambda (n) ;n: string
    (let ((k (- (string-length n) 1))
          )
    (if (= (string-length n) 0)
          0
          (+ (* 2 (intera (substring n 0 k))) (bit-rep->number (substring n k)))
          )
      )
    ))

(define frazionaria ;val: int
  (lambda (n) ;n: string
   (frazionaria-rec n 1/2)
    ))

(define frazionaria-rec ;val: int
  (lambda (n k) ;n: string, k: int
    (if (= (string-length n) 0)
        0
        (+ (frazionaria-rec (substring n 1) (* 1/2 k)) (* k (bit-rep->number (substring n 0 1)))) 
          ;(+ (* (expt 2 (- 0 k)) (frazionaria (substring n 0 k))) (bit-rep->number (substring n k)))
          )
    ))
  
(define punto ;val: int
  (lambda (n k s) ;n: string, s,k: int
    (cond ((= k s) (bin-rep->number (substring n 0 k)))
          (else (intera n k s))
          )
    ))

(define bit-rep->number ;val: int
  (lambda (digit) ;digit: string
    (cond ((string=? digit "0") 0)
          ((string=? digit "1") 1)
          ((string=? digit "2") 2)
          ((string=? digit "3") 3)
          ((string=? digit "4") 4)
          ((string=? digit "5") 5)
          ((string=? digit "6") 6)
          ((string=? digit "7") 7)
          ((string=? digit "8") 8)
          ((string=? digit "9") 9)
          ((string=? digit "A") 10)
          ((string=? digit "B") 11)
          ((string=? digit "C") 12)
          ((string=? digit "D") 13)
          ((string=? digit "E") 14)
          ((string=? digit "F") 15)
          )
    ))

(define verificapunto ;val: boolean
  (lambda (s) ;s: string
    (if (= (string-length s) 0)
        false
        (if (string=? (substring s 0 1) ".")
            true
            (verificapunto (substring s 1))
        )
        )
    ))
(define posizionepunto ;val: int
  (lambda (s) ;s: string
    (posizionepunto-rec s 0)
    ))

(define posizionepunto-rec ;val: int
  (lambda (s k) ;s:string, k: int
    (if (= (string-length s) 0)
        -1
        (if (string=? (substring s 0 1) ".")
            k
            (posizionepunto-rec (substring s 1) (+ k 1))
        )
        )
    ))
(define digit-val ; val:intero >= 0
  (lambda (base digit) ; symb, num:stringhe
    (if (string=? (substring base 0 1) digit)
        0
        (+ 1 (digit-val (substring base 1) digit)))
    ))

(define rep->number ;val: intero
  (lambda (u v) ;u, v: string
    (let ((b (base u))
          )
    (cond ((string=? (substring v 0 1) "+")
                (calcolo-valore-bin2 (substring v 1) u b))
               ((string=? (substring v 0 1) "-")
                (* (calcolo-valore-bin2 (substring v 1) u b) -1))
               (else
                (calcolo-valore-bin2 v u b))
               )
    )
    ))
(define calcolo-valore-bin2
  (lambda (n u b)
    (let ((s (posizionepunto n))
            )
      (if (= s -1)
          (intera2 n u b);non ho il punto --- intera n
          (+ (intera2 (substring n 0 s) u b) (frazionaria2 (substring n (+ s 1)) u b));ho il punto --- intera da primo elemento a quello prima del punto + frazionaria da elemento dopo il punto in poi
          )
      )
    ))
(define base (lambda (s) (string-length s)))
(define intera2
  (lambda (n u b)
    (let ((k (- (string-length n) 1))
          )
    (if (= (string-length n) 0)
          0
          (+ (* b (intera2 (substring n 0 k) u b)) (digit-val  u (substring n k)))
          )
      )
    ))

(define frazionaria2
  (lambda (n u b)
   (frazionaria-rec2 n u (/ 1 b))
    ))

(define frazionaria-rec2
  (lambda (n u k)
    (if (= (string-length n) 0)
        0
        (+ (frazionaria-rec2 (substring n 1) u (* 1/2 k)) (* k (digit-val  u (substring n 0 1)))) 
          )
    ))
;(define pos-digit-0 (char->integer #\0))